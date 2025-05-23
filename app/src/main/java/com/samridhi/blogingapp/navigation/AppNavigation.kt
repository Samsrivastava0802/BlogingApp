package com.samridhi.blogingapp.navigation

import com.samridhi.blogingapp.utils.addRouteArgument

object AppArgs {
    const val ARG_URL = "url"
}
sealed class AppScreen(val name: String, val route: String) {
    data object HomeScreen : AppScreen("home", "home")
    data object BrowserScreen : AppScreen(
        "browser", "browser".addRouteArgument(AppArgs.ARG_URL)
    )
}