package com.crs.bluered.ui.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.crs.bluered.features.deck.list.presentation.DeckListScreen
import com.crs.bluered.features.deck.list.presentation.DeckListViewModel
import com.crs.bluered.ui.navigation.screens.MainScreens

fun NavGraphBuilder.mainScreen() {
    composable<MainScreens.HomeScreen> {

        val viewModel: DeckListViewModel = hiltViewModel<DeckListViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        DeckListScreen(
            uiState = uiState,
            onLoadIfNeeded = viewModel::loadIfNeeded,
            onRefresh = viewModel::refresh,
            onLoadMore = viewModel::loadMore,
            onChangeVisibility = viewModel::changeVisibility
        )

    }
}

fun NavController.navigateToMainGraph(
    navOptions: NavOptions? = null
) {
    navigate(MainScreens.HomeScreen, navOptions)
}