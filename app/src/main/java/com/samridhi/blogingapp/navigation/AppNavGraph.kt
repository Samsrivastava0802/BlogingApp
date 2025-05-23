package com.samridhi.blogingapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.samridhi.blogingapp.presentation.browserscreen.BrowserScreen
import com.samridhi.blogingapp.presentation.home.HomeScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String = AppScreen.HomeScreen.route,
    navActions: AppNavigationActions
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ){
        composable(
            route = AppScreen.HomeScreen.route
        ) {
           HomeScreen(onAction = navActions::navigateFromHomeScreen)
        }
        composable(
            route = AppScreen.BrowserScreen.route
        ) {
            val data = it.arguments?.getString(AppArgs.ARG_URL)
            BrowserScreen(
                 onAction = navActions::navigateFromBrowserScreen,
                 url = data ?: ""
             )
        }
    }
}