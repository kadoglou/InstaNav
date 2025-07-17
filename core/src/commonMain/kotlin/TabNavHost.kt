import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.zIndex
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun <T> TabNavHost(
    navHostController: NavHostController,
    startDestination: T,
    isVisible: Boolean,
    builder: NavGraphBuilder.() -> Unit,
    state: InstaNavHostState<T>
) {
    HandleBackPress(
        isVisible = isVisible,
        state = state,
        navHostController = navHostController,
    )
    NavHost(
        modifier = Modifier.visible(isVisible),
        navController = navHostController,
        startDestination = startDestination.toString()
    ) {
        builder()
    }
}


private fun Modifier.visible(isVisible: Boolean): Modifier = this.then(
    if (isVisible) Modifier else Modifier.zIndex(-1f).alpha(0f).pointerInput(Unit) {
        awaitPointerEventScope {
            // we should wait for all new pointer events
            while (true) {
                awaitPointerEvent(pass = PointerEventPass.Initial)
                    .changes
                    .forEach(PointerInputChange::consume)
            }
        }
    }
)