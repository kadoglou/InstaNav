package dev.kadoglou.instanav

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val GLOBAL_DURATION = 500
const val BACKGROUND_SCREEN_OFFSET_TRANSITION = 400

fun NavGraphBuilder.animatableComposable(
    route: String,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(durationMillis = GLOBAL_DURATION)
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -BACKGROUND_SCREEN_OFFSET_TRANSITION },
                animationSpec = tween(durationMillis = GLOBAL_DURATION)
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -BACKGROUND_SCREEN_OFFSET_TRANSITION },
                animationSpec = tween(durationMillis = GLOBAL_DURATION)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(durationMillis = GLOBAL_DURATION)
            )
        },
        content = content
    )
}
