package com.crs.bluered.ui.navigation.screens

import kotlinx.serialization.Serializable

@Serializable
sealed interface Graphs {
    @Serializable
    data object AuthGraph : Graphs

    @Serializable
    data object MainGraph : Graphs
}

@Serializable
sealed interface AuthScreens {
    @Serializable
    data object LoginScreen: AuthScreens
    @Serializable
    data object RegisterScreen: AuthScreens
    @Serializable
    data object ConfirmPasswordScreen: AuthScreens
    @Serializable
    data object ForgotPasswordScreen: AuthScreens
    @Serializable
    data object ValidateCodeScreen: AuthScreens
}

@Serializable
sealed interface MainScreens {
    @Serializable
    data object HomeScreen: MainScreens

    @Serializable
    data object CreateDeckScreen: MainScreens

    @Serializable
    data class CardScreen(val deckId: String, val deckTitle: String): MainScreens
}
