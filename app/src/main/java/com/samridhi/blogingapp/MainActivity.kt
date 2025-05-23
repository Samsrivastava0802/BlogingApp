package com.samridhi.blogingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.samridhi.blogingapp.navigation.AppNavGraph
import com.samridhi.blogingapp.navigation.AppNavigationActions
import com.samridhi.blogingapp.ui.theme.BlogingAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlogingAppTheme {
                BloggingApp(
                    onNavigationEnd = {
                        finish()
                    }
                )
            }
        }
    }
}

@Composable
fun BloggingApp(
    onNavigationEnd: () -> Unit,
) {
    val navController = rememberNavController()
    val navActions: AppNavigationActions = remember(navController) {
        AppNavigationActions(navController, onNavigationEnd)
    }
    AppNavGraph(
        navController = navController,
        navActions = navActions
    )
}
