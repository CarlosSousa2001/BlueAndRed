package com.crs.bluered.ui.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.crs.bluered.ui.navigation.loginScreen
import com.crs.bluered.ui.navigation.registerScreen
import com.crs.bluered.ui.navigation.screens.AuthScreens
import com.crs.bluered.ui.navigation.screens.Graphs

fun NavGraphBuilder.authGraph(
    onNavigateToMainGraph: (NavOptions) -> Unit,
    onNavigateToLoginScreen: () -> Unit,
    onNavigateToRegisterScreen: () -> Unit,
    onNavigateToForgotPasswordScreen: () -> Unit,
    onNavigateToValidateCodeScreen: () -> Unit,
    onNavigateToConfirmPasswordScreen: () -> Unit
) {
    navigation<Graphs.AuthGraph>(
        startDestination = AuthScreens.LoginScreen,
    ) {
        loginScreen(
            onNavigateToMainGraph = {
                onNavigateToMainGraph(navOptions {
                    popUpTo(Graphs.AuthGraph)
                })
            },
            onNavigateToRegisterScreen = onNavigateToRegisterScreen,
            onNavigateToForgotPasswordScreen = onNavigateToForgotPasswordScreen
        )

        registerScreen(
            onNavigateToLoginScreen = onNavigateToLoginScreen
        )
    }
}
