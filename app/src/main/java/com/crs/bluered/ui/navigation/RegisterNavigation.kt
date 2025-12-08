package com.crs.bluered.ui.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.crs.bluered.features.auth.register.presentation.RegisterScreen
import com.crs.bluered.features.auth.register.presentation.RegisterViewModel
import com.crs.bluered.ui.navigation.screens.AuthScreens

fun NavGraphBuilder.registerScreen(
    onNavigateToLoginScreen: () -> Unit
) {
    composable<AuthScreens.RegisterScreen> {

        val viewModel: RegisterViewModel = hiltViewModel<RegisterViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        RegisterScreen(
            uiState = uiState,
            onEvent = viewModel::onEvent,
            onNavigationToLoginScreen = onNavigateToLoginScreen
        )
    }
}

fun NavController.navigateToRegisterScreen(){
    navigate(AuthScreens.RegisterScreen)
}