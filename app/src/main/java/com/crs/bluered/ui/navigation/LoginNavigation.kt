package com.crs.bluered.ui.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.crs.bluered.features.auth.login.presentation.LoginScreen
import com.crs.bluered.features.auth.login.presentation.LoginViewModel
import com.crs.bluered.ui.navigation.screens.AuthScreens

fun NavGraphBuilder.loginScreen(
    onNavigateToMainGraph: () -> Unit,
    onNavigateToRegisterScreen: () -> Unit,
    onNavigateToForgotPasswordScreen: () -> Unit,
) {
    composable<AuthScreens.LoginScreen> {

        val viewModel: LoginViewModel = hiltViewModel<LoginViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        LoginScreen(
            uiState = uiState,
            onEvent = viewModel::onEvent,
            onNavigateToRegisterScreen = onNavigateToRegisterScreen,
            onNavigateToMainGraph = onNavigateToMainGraph,
            onNavigateToForgotPasswordScreen = onNavigateToForgotPasswordScreen
        )
    }
}

fun NavController.navigateToLoginScreen(){
    navigate(AuthScreens.LoginScreen){
        popUpTo(0)
    }
}