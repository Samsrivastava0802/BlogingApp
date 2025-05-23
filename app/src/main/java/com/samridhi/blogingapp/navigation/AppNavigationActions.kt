package com.samridhi.blogingapp.navigation

import androidx.navigation.NavController
import com.samridhi.blogingapp.utils.withArg

class AppNavigationActions(
    private val navController: NavController,
    private val onFinish: () -> Unit
) {
    private fun back() {
        navController.popBackStack()
    }

    private fun finishActivity() {
        onFinish()
    }

    fun navigateFromHomeScreen(actions: HomeScreenActions) {
        when (actions) {
            is HomeScreenActions.OpenBrowserScreen -> {
                navController.navigate(
                    AppScreen.BrowserScreen.name
                        .withArg(AppArgs.ARG_URL, actions.url)
                )
            }
        }
    }
    fun navigateFromBrowserScreen(actions: BrowserScreenScreenActions) {
        when (actions) {
            BrowserScreenScreenActions.OnBack -> {
                back()
            }
        }
    }
}