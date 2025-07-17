import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController

val LocalInstaNavController = compositionLocalOf<NavHostController> {
    error("No NavController provided for MultiTabScaffold! Make sure to use `CompositionLocalProvider`.")
}