package dev.kadoglou.instanav

import InstaNavHost
import InstaTab
import LocalInstaNavController
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import rememberInstaNavState

@Composable
fun App() {

    // region InstaNav initialization parameters

    val tabs = listOf<InstaTab<String>>(
        InstaTab("tab_1", ScreenA.route),
        InstaTab("tab_2", ScreenB.route),
        InstaTab("tab_3", ScreenC.route),
        InstaTab("tab_4", ScreenD.route),
    )
    val instaNavState = rememberInstaNavState<String>(initialTabs = listOf("tab_1"))

    val controllers = List(tabs.size) { rememberNavController() }
    val tabNavControllers = remember(controllers) {
        controllers.mapIndexed { i, controller -> tabs[i].tabRoute to controller }.toMap()
    }

    // endregion

    CompositionLocalProvider(
        LocalInstaNavController provides tabNavControllers.getValue(instaNavState.currentTab),
    ) {

        MaterialTheme {
            Scaffold(
                modifier = Modifier,
                bottomBar = {
                    BottomBar(
                        currentTab = instaNavState.currentTab,
                        tabs = tabs
                    ) { instaNavState.navigateTo(it) }
                }
            ) { padding ->
                Box(modifier = Modifier.padding(padding)) {

                    // region InstaNavHost usage

                    InstaNavHost<String>(
                        state = instaNavState,
                        tabs = tabs,
                        tabNavControllers = tabNavControllers
                    ) {
                        composable(route = ScreenA.route) {
                            ScreenA().context()
                        }

                        composable(route = ScreenB.route) {
                            ScreenB().context()
                        }

                        composable(route = ScreenC.route) {
                            ScreenC().context()
                        }

                        composable(route = ScreenD.route) {
                            ScreenD().context()
                        }

                        animatableComposable(route = SubScreenA.route) {
                            SubScreenA().context()
                        }

                        animatableComposable(route = SubScreenB.route) {
                            SubScreenB().context()
                        }

                        animatableComposable(route = SubScreenC.route) {
                            SubScreenC().context()
                        }
                    }

                    // endregion
                }
            }
        }
    }
}