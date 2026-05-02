package com.crs.bluered.ui.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.crs.bluered.ui.navigation.cardScreen
import com.crs.bluered.ui.navigation.createCardScreen
import com.crs.bluered.ui.navigation.createDeckScreen
import com.crs.bluered.ui.navigation.editDeckScreen
import com.crs.bluered.ui.navigation.mainScreen
import com.crs.bluered.ui.navigation.screens.Graphs
import com.crs.bluered.ui.navigation.screens.MainScreens

fun NavGraphBuilder.mainGraph(
    onNavigationToMainListScreen: () -> Unit,
    onNavigateToCreateDeckScreen: () -> Unit,
    onNavigateToCardScreen: (
        deckId: String,
        deckTitle: String,
        canEdit: Boolean,
        visibility: String,
        maxMembers: Int?
    ) -> Unit,
    onNavigateToCreateCardScreen: (deckId: String) -> Unit,
    onNavigateToEditDeckScreen: (
        deckId: String,
        deckTitle: String,
        visibility: String,
        maxMembers: Int?
    ) -> Unit
){
    navigation<Graphs.MainGraph>(
        startDestination = MainScreens.HomeScreen
    ) {
        mainScreen(
            onNavigateToCreateDeckScreen = onNavigateToCreateDeckScreen,
            onNavigateToCardScreen = onNavigateToCardScreen
        )
        createDeckScreen(
            onNavigationToMainListScreen = onNavigationToMainListScreen
        )
        cardScreen(
            onNavigateBack = onNavigationToMainListScreen,
            onNavigateToCreateCardScreen = onNavigateToCreateCardScreen,
            onNavigateToEditDeckScreen = onNavigateToEditDeckScreen
        )
        createCardScreen(
            onNavigationToMainListScreen = onNavigationToMainListScreen
        )
        editDeckScreen(
            onNavigateBack = onNavigationToMainListScreen
        )
    }
}
