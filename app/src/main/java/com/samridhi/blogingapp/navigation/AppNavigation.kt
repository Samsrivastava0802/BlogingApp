package com.samridhi.blogingapp.navigation

sealed class AppScreen(val name: String, val route: String) {
    data object HomeScreen : AppScreen("home", "home")
    data object BlogDetailScreen : AppScreen(
        "blogDetail", "blogDetail"
    )
}