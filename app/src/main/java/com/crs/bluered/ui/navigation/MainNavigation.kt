package com.crs.bluered.ui.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.crs.bluered.features.deck.list.domain.model.DeckListItem
import com.crs.bluered.features.deck.list.presentation.DeckListEvent
import com.crs.bluered.features.deck.list.presentation.DeckListScreen
import com.crs.bluered.features.deck.list.presentation.DeckListViewModel
import com.crs.bluered.shared.domain.enums.DeckListScope
import com.crs.bluered.ui.navigation.screens.MainScreens

fun NavGraphBuilder.mainScreen(
    onNavigateToCreateDeckScreen: () -> Unit,
    onNavigateToCardScreen: (
        deckId: String,
        deckTitle: String,
        canEdit: Boolean,
        visibility: String,
        maxMembers: Int?
    ) -> Unit
) {
    composable<MainScreens.HomeScreen> {

        val viewModel: DeckListViewModel = hiltViewModel<DeckListViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        DeckListScreen(
            uiState = uiState,
            onLoadIfNeeded = { viewModel.onEvent(DeckListEvent.LoadIfNeeded) },
            onRefresh = { viewModel.onEvent(DeckListEvent.Refresh) },
            onLoadMore = { viewModel.onEvent(DeckListEvent.LoadMore) },
            onChangeScope = { viewModel.onEvent(DeckListEvent.ChangeScope(it)) },
            onNavigateToCreateDeckScreen = onNavigateToCreateDeckScreen,
            onDeckClick = { item: DeckListItem ->
                onNavigateToCardScreen(
                    item.id,
                    item.title,
                    uiState.scope == DeckListScope.ME,
                    item.visibility.apiValue,
                    item.maxMembers
                )
            }
        )
    }
}

fun NavController.navigateToMainGraph(
    navOptions: NavOptions? = null
) {
    navigate(MainScreens.HomeScreen, navOptions)
}
