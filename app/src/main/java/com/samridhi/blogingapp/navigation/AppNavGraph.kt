package com.samridhi.blogingapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

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

        }
        composable(
            route = AppScreen.BlogDetailScreen.route
        ) {

        }
    }

}