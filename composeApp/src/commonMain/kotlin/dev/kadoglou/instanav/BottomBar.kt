package dev.kadoglou.instanav

import InstaTab
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun BottomBar(
    currentTab: String,
    tabs: List<InstaTab<String>>,
    onNavigate: (String) -> Unit,
) {
    CompositionLocalProvider(LocalRippleConfiguration provides null) {
        NavigationBar(
            modifier = Modifier.background(Color.DarkGray).padding(horizontal = 10.dp),
            containerColor = Color.DarkGray
        ) {
            tabs.forEach { tab ->
                val isSelected = currentTab == tab.tabRoute
                NavigationBarItem(
                    selected = isSelected,
                    label = {
                        Text(
                            tab.tabRoute,
                            color = if (isSelected) Color.White else Color.Red
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent,
                    ),
                    icon = {},
                    onClick = { onNavigate(tab.tabRoute) }
                )
            }
        }
    }
}
