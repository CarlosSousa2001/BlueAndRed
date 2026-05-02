package com.crs.bluered.ui.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.crs.bluered.features.deck.edit.presentation.EditDeckScreen
import com.crs.bluered.features.deck.edit.presentation.EditDeckViewModel
import com.crs.bluered.ui.navigation.screens.MainScreens

fun NavGraphBuilder.editDeckScreen(
    onNavigateBack: () -> Unit
) {
    composable<MainScreens.EditDeckScreen> {
        val viewModel: EditDeckViewModel = hiltViewModel<EditDeckViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        EditDeckScreen(
            uiState = uiState,
            onEvent = viewModel::onEvent,
            onNavigateBack = onNavigateBack
        )
    }
}

fun NavController.navigateToEditDeckScreen(
    deckId: String,
    deckTitle: String,
    visibility: String,
    maxMembers: Int?
) {
    navigate(
        MainScreens.EditDeckScreen(
            deckId = deckId,
            deckTitle = deckTitle,
            visibility = visibility,
            maxMembers = maxMembers
        )
    )
}
