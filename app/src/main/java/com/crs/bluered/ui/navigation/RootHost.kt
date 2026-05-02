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
                if (!navController.popBackStack()) {
                    navController.navigateToMainGraph()
                }
            },
            onNavigateToCreateDeckScreen = {
                navController.navigateToCreateDeckScreen()
            },
            onNavigateToCardScreen = { deckId, deckTitle, canEdit, visibility, maxMembers ->
                navController.navigateToCardScreen(
                    deckId = deckId,
                    deckTitle = deckTitle,
                    canEdit = canEdit,
                    visibility = visibility,
                    maxMembers = maxMembers
                )
            },
            onNavigateToEditDeckScreen = { deckId, deckTitle, visibility, maxMembers ->
                navController.navigateToEditDeckScreen(
                    deckId = deckId,
                    deckTitle = deckTitle,
                    visibility = visibility,
                    maxMembers = maxMembers
                )
            }
        )
    }
}
