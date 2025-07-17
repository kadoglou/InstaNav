import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
internal actual fun <T> HandleBackPress(
    isVisible: Boolean,
    state: InstaNavHostState<T>,
    navHostController: NavHostController
) {
    if (state.openTabs.size > 1 && isVisible) {
        BackHandler(enabled = true) {
            if (navHostController.previousBackStackEntry != null) {
                /**
                 * If the current [TabNavHost] has routes to its stack, we navigate back to that
                 * specific [TabNavHost].
                 */
                navHostController.popBackStack()
            } else {
                /**
                 * If there is no other route in the current [TabNavHost], we navigate to the
                 * previous tab.
                 */
                state.popBackTabStack()
            }
        }
    }
}