package com.crs.bluered.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.crs.bluered.ui.navigation.screens.MainScreens

fun NavGraphBuilder.mainScreen() {
    composable<MainScreens.HomeScreen> {
        Column {
            Text("Home Testando...")
            Button(onClick = { /* nada */ }) {
                Text("Clique")
            }
        }
    }
}

fun NavController.navigateToMainGraph(
    navOptions: NavOptions? = null
) {
    navigate(MainScreens.HomeScreen, navOptions)
}