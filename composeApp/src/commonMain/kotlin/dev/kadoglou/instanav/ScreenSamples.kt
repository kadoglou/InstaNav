package dev.kadoglou.instanav

import LocalInstaNavController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

// region Screen samples

class ScreenA() {
    companion object {
        val route = "screen_a"
    }

    @Composable
    fun context() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Screen A")
        }
    }
}

class ScreenB() {
    companion object {
        val route = "screen_b"
    }

    @Composable
    fun context() {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Screen B")
        }
    }
}

class ScreenC() {
    companion object {
        val route = "screen_c"
    }

    @Composable
    fun context() {
        val instaNavController = LocalInstaNavController.current

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Screen C")
            Button(onClick = { instaNavController.navigate(SubScreenA.route) }) {
                Text("Navigate to Sub Screen A")
            }
        }
    }
}

class ScreenD() {
    companion object {
        val route = "screen_d"
    }

    @Composable
    fun context() {
        val instaNavController = LocalInstaNavController.current
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Screen D")
            Button(onClick = { instaNavController.navigate(SubScreenC.route) }) {
                Text("Navigate to Sub Screen C")
            }
        }
    }
}

class SubScreenA() {
    companion object {
        val route = "sub_screen_a"
    }

    @Composable
    fun context() {
        val instaNavController = LocalInstaNavController.current
        Column(
            modifier = Modifier.fillMaxSize().background(Color.Blue),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Sub Screen A")
            Button(onClick = { instaNavController.navigate(SubScreenB.route) }) {
                Text("Navigate to Sub Screen B")
            }
        }
    }

}

class SubScreenB() {
    companion object {
        val route = "sub_screen_b"
    }

    @Composable
    fun context() {

        Column(
            modifier = Modifier.fillMaxSize().background(Color.Red),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Sub Screen B")
        }
    }
}

class SubScreenC() {
    companion object {
        val route = "sub_screen_c"
    }

    @Composable
    fun context() {

        Column(
            modifier = Modifier.fillMaxSize().background(Color.Green),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Sub Screen C")
        }
    }
}

// endregion