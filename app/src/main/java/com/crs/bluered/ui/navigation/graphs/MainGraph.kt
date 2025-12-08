package com.crs.bluered.ui.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.crs.bluered.ui.navigation.mainScreen
import com.crs.bluered.ui.navigation.screens.Graphs
import com.crs.bluered.ui.navigation.screens.MainScreens

fun NavGraphBuilder.mainGraph(
    onNavigateAnotherScreen: () -> Unit,
){
    navigation<Graphs.MainGraph>(
        startDestination = MainScreens.HomeScreen
    ) {
        mainScreen()
    }
}