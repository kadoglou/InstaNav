import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

/**
 * A composable that uses multiple [TabNavHost] instances, one per tab, and ensures that each tab
 * maintains its own independent back stack — mimicking the behaviour seen in apps like Instagram or YouTube.
 *
 * This function only composes the tabs currently listed in [InstaNavHostState.openTabs].
 * When a tab is dismissed (i.e., popped from the stack), it is no longer composed,
 * freeing up memory and ensuring its state is reset when revisited later.
 *
 * @param state The [InstaNavHostState] that manages tab navigation logic and tab order.
 * @param tabs The list of all available tabs. Each tab includes a route and its starting destination.
 * @param tabNavControllers A mapping of each tab to its [NavHostController]. One controller per tab is required.
 * @param builder The shared navigation graph builder used to declare all screens used in every tab.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun <T> InstaNavHost(
    state: InstaNavHostState<T>,
    tabs: List<InstaTab<T>>,
    tabNavControllers: Map<T, NavHostController>,
    builder: NavGraphBuilder.() -> Unit
) {
    tabNavControllers.forEach { (tab, navController) ->
        /**
         * We want to compose only the tabs we still have open.
         * If a tab is dismissed from its starting screen, the tab will close.
         *
         * This saves memory usage and forces a refresh of the tab
         * when reopened after being removed.
         */
        if (state.openTabs.contains(tab)) {
            val tabInfo = tabs.firstOrNull { it.tabRoute == tab } ?: return@forEach
            TabNavHost(
                navHostController = navController,
                startDestination = tabInfo.startingRoute,
                isVisible = state.currentTab == tab,
                builder = builder,
                state = state
            )
        }
    }
}