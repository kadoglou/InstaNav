import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable

/**
 * A navigation state holder for multi-tab apps like Instagram or YouTube.
 *
 * It manages a stack of tabs where each tab maintains its own navigation state. The latest selected
 * tab is always at the end of the list, and is considered the currently visible tab.
 *
 * @param T The type used to represent a tab (typically a screen or destination object).
 * @property homeTab The default tab to fall back to when only one tab remains.
 * @property tabListState A state holder containing the ordered list of open tabs.
 */
class InstaNavHostState<T> internal constructor(
    private val homeTab: T,
    private val tabListState: MutableState<List<T>>
) {

    /**
     * We keep the latest open tab in the last position of the list. By returning the last item
     * of the tabList we are returning the current tab.
     */
    val currentTab: T
        get() = tabListState.value.last()

    /**
     * Keeps a list of the visited tabs. It is used to compose the tabs in the background to not reset
     * the [androidx.navigation.NavHostController] of that tab.
     */
    val openTabs: List<T>
        get() = tabListState.value

    /**
     * Switches the order of the latest tab, making the last selected tab to be visible on the screen.
     */
    fun navigateTo(tab: T) {
        tabListState.value = tabListState.value
            .filterNot { it == tab }
            .plus(tab)
    }

    /**
     * Removes the latest (currently active) tab of the list. In case [homeTab] is dismissed that
     * has still other open tabs, we re-add it to the list in the first position. We want the home
     * tab to be the last visited tab before closing the app.
     */
    fun popBackTabStack() {
        val updated = tabListState.value.dropLast(1)
        val finalList = if (updated.size == 1 && homeTab !in updated) {
            buildList {
                add(homeTab)
                addAll(updated)
            }
        } else updated

        tabListState.value = finalList
    }
}

/**
 * Returns a [Saver] for saving and restoring [InstaNavHostState] across recompositions.
 *
 * Useful for remembering state through process death or configuration changes.
 * You can provide custom save/restore logic for complex tab types.
 *
 * @param saveTab Function to convert a tab to a String for saving.
 * @param restoreTab Function to convert a saved String back into a tab object.
 */
fun <T> InstaNavHostSaver(
    saveTab: (T) -> String = { it.toString() },
    restoreTab: (String) -> T?
): Saver<InstaNavHostState<T>, List<String>> {
    return Saver(
        save = { it.openTabs.map(saveTab) },
        restore = { names ->
            val restored = names.mapNotNull(restoreTab)
            val home = restored.firstOrNull() ?: return@Saver null
            InstaNavHostState(home, mutableStateOf(restored))
        }
    )
}

/**
 * Remembers an [InstaNavHostState] instance for use in multi-tab navigation.
 *
 * You can provide optional `saveTab` and `restoreTab` lambdas to enable state restoration.
 * If not provided, the state will only be held in memory and not survive process death.
 *
 * @param initialTabs The initial set of tabs to open. The first item is treated as the home tab.
 * @param saveTab Optional lambda for saving a tab to String.
 * @param restoreTab Optional lambda for restoring a tab from String.
 */
@Composable
fun <T> rememberInstaNavState(
    initialTabs: List<T>,
    saveTab: ((T) -> String)? = null,
    restoreTab: ((String) -> T)? = null,
): InstaNavHostState<T> {
    require(initialTabs.isNotEmpty()) { "At least one initial tab is required." }

    return if (saveTab != null && restoreTab != null) {
        rememberSaveable(saver = InstaNavHostSaver(saveTab, restoreTab)) {
            InstaNavHostState(initialTabs.first(), mutableStateOf(initialTabs))
        }
    } else {
        // No saving – just remember in memory
        remember {
            InstaNavHostState(initialTabs.first(), mutableStateOf(initialTabs))
        }
    }
}