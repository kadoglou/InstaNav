import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
internal expect fun <T> HandleBackPress(
    isVisible: Boolean,
    state: InstaNavHostState<T>,
    navHostController: NavHostController
)