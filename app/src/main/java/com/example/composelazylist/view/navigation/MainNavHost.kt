package com.example.composelazylist.view.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composelazylist.R
import com.example.composelazylist.utils.getScreenNameFromRoute
import com.example.composelazylist.view.DetailsScreen
import com.example.composelazylist.view.HomeScreen
import com.example.composelazylist.view.MainAction
import com.example.composelazylist.view.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavHost(
    navHostController: NavHostController,
    startScreens: String = Screens.HOME.name,
    mainViewModel: MainViewModel,
    onAction: (MainAction) -> Unit = {}
) {
    var currentScreen by remember {
        mutableStateOf<Screens>(
            Screens.entries.toMutableList().firstOrNull { it.name == startScreens } ?: Screens.HOME
        )
    }
    LaunchedEffect(Unit) {
        navHostController.addOnDestinationChangedListener { controller, destination, arguments ->
            currentScreen = Screens.entries.toMutableList()
                .firstOrNull {
                    it.name.hashCode() == navHostController.currentDestination?.route?.getScreenNameFromRoute()
                        .hashCode()
                }
                ?: Screens.HOME
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.default_space_8)),
                title = { Text(text = stringResource(currentScreen.getTitle())) },
                navigationIcon = {
                    Icon(
                        painter = painterResource(android.R.drawable.ic_menu_close_clear_cancel),
                        modifier = Modifier.clickable(onClick = {
                            onAction.invoke(MainAction.OnBack)
                        }),
                        contentDescription = null
                    )
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            NavHost(
                navController = navHostController,
                startDestination = startScreens
            ) {
                composable(route = Screens.HOME.name) {
                    HomeScreen(mainViewModel, onAction)
                }
                composable(
                    route = Screens.DETAILS.name
                ) {
                    mainViewModel.selectedUserItem
                        ?.let { userItem -> DetailsScreen(userItem) }
                }
            }
        }
    }
}