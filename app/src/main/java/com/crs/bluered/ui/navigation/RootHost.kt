package com.crs.bluered.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.crs.bluered.ui.navigation.graphs.authGraph
import com.crs.bluered.ui.navigation.graphs.mainGraph
import com.crs.bluered.ui.navigation.screens.Graphs

@Composable
fun RootHost(
    startDestination: Graphs,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        authGraph(
            onNavigateToMainGraph = { navOptions ->
                navController.navigateToMainGraph(navOptions)
            },
            onNavigateToLoginScreen = {
                navController.navigateToLoginScreen()
            },
            onNavigateToRegisterScreen = {
                navController.navigateToRegisterScreen()
            },
            onNavigateToForgotPasswordScreen = {},
            onNavigateToValidateCodeScreen = {},
            onNavigateToConfirmPasswordScreen = {}
        )

        mainGraph(
            onNavigationToMainListScreen = {
                navController.navigateToMainGraph()
            },
            onNavigateToCreateDeckScreen = {
                navController.navigateToCreateDeckScreen()
            }
        )
    }
}