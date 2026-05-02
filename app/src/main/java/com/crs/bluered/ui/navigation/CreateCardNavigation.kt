package com.crs.bluered.ui.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.crs.bluered.features.card.create.presentation.CreateCardScreen
import com.crs.bluered.features.card.create.presentation.CreateCardViewModel
import com.crs.bluered.ui.navigation.screens.MainScreens

fun NavGraphBuilder.createCardScreen(
    onNavigationToMainListScreen: () -> Unit
) {
    composable<MainScreens.CreateCardScreen> {
        val viewModel: CreateCardViewModel = hiltViewModel<CreateCardViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        CreateCardScreen(
            uiState = uiState,
            onEvent = viewModel::onEvent,
            onNavigationToMainListScreen = onNavigationToMainListScreen
        )
    }
}

fun NavController.navigateToCreateCardScreen(deckId: String){
    navigate(MainScreens.CreateCardScreen(deckId = deckId))
}