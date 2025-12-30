package com.crs.bluered.ui.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.crs.bluered.features.deck.create.presentation.CreateDeckScreen
import com.crs.bluered.features.deck.create.presentation.CreateDeckViewModel
import com.crs.bluered.ui.navigation.screens.MainScreens

fun NavGraphBuilder.createDeckScreen(
    onNavigationToMainListScreen: () -> Unit
) {
    composable<MainScreens.CreateDeckScreen> {
        val viewModel: CreateDeckViewModel = hiltViewModel<CreateDeckViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        CreateDeckScreen(
            uiState = uiState,
            onEvent = viewModel::onEvent,
            onNavigationToMainListScreen = onNavigationToMainListScreen
        )
    }
}

fun NavController.navigateToCreateDeckScreen(){
    navigate(MainScreens.CreateDeckScreen)
}