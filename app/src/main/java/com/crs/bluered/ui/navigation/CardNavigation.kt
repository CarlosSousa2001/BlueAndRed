package com.crs.bluered.ui.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.crs.bluered.features.card.list.presentation.CardListEvent
import com.crs.bluered.features.card.list.presentation.CardListScreen
import com.crs.bluered.features.card.list.presentation.CardListViewModel
import com.crs.bluered.ui.navigation.screens.MainScreens

fun NavGraphBuilder.cardScreen(
    onNavigateBack: () -> Unit,
    onNavigateToCreateCardScreen: (deckId: String) -> Unit,
    onNavigateToEditDeckScreen: (
        deckId: String,
        deckTitle: String,
        visibility: String,
        maxMembers: Int?
    ) -> Unit
) {
    composable<MainScreens.CardScreen> {
        val viewModel: CardListViewModel = hiltViewModel<CardListViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        CardListScreen(
            uiState = uiState,
            onLoadIfNeeded = { viewModel.onEvent(CardListEvent.LoadIfNeeded) },
            onRefresh = { viewModel.onEvent(CardListEvent.Refresh) },
            onLoadMore = { viewModel.onEvent(CardListEvent.LoadMore) },
            onNavigateBack = onNavigateBack,
            onNavigateToEditDeckScreen = {
                onNavigateToEditDeckScreen(
                    uiState.deckId,
                    uiState.deckTitle,
                    uiState.visibility,
                    uiState.maxMembers
                )
            },
            onNavigateToCreateCardScreen = { onNavigateToCreateCardScreen(uiState.deckId) },
            onStartGameplay = {}
        )
    }
}

fun NavController.navigateToCardScreen(
    deckId: String,
    deckTitle: String,
    canEdit: Boolean,
    visibility: String,
    maxMembers: Int?
) {
    navigate(
        MainScreens.CardScreen(
            deckId = deckId,
            deckTitle = deckTitle,
            canEdit = canEdit,
            visibility = visibility,
            maxMembers = maxMembers
        )
    )
}
