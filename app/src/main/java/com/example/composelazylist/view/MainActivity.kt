package com.example.composelazylist.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composelazylist.ui.theme.InterviewApplicationTheme
import com.example.composelazylist.utils.getScreenNameFromRoute
import com.example.composelazylist.view.navigation.MainNavHost
import com.example.composelazylist.view.navigation.Screens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    var startScreen = Screens.HOME.name
    lateinit var navHostController: NavHostController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getUserList()
        enableEdgeToEdge(
            SystemBarStyle.light(
                scrim = Color.Black.hashCode(),
                darkScrim = Color.Black.hashCode()
            )
        )
        setContent {
            navHostController = rememberNavController()
            InterviewApplicationTheme {
                MainNavHost(
                    navHostController = navHostController,
                    startScreens = startScreen,
                    mainViewModel = viewModel,
                    onAction = {
                        onAction(it)
                    }
                )
            }
        }
    }

    private fun onAction(action: MainAction) {
        when (action) {
            MainAction.OnBack -> {
                if (navHostController.currentDestination?.route.getScreenNameFromRoute()
                        .hashCode() == startScreen.hashCode()
                ) {
                    finish()
                } else {
                    navHostController.popBackStack()
                }
            }

            is MainAction.OpenDetails -> {
                navHostController.navigate(route = "${Screens.DETAILS.name}/${action.userItem.id}")
            }
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InterviewApplicationTheme {
        Greeting("Android")
    }
}