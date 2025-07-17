import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.backhandler.BackHandler
import androidx.navigation.NavHostController

/**
 * iOS implementation does not navigate to previous tab when we reach the root of that [TabNavHost],
 * mimicking the way iOS native navigation works.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal actual fun <T> HandleBackPress(
    isVisible: Boolean,
    state: InstaNavHostState<T>,
    navHostController: NavHostController,
) {
    if (state.openTabs.size > 1 && isVisible) {
        BackHandler(enabled = true) {
            if (navHostController.previousBackStackEntry != null) {
                navHostController.popBackStack()
            }
        }
    }
}