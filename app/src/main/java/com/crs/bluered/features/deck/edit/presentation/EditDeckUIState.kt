package com.crs.bluered.features.deck.edit.presentation

import com.crs.bluered.core.domain.model.InputFieldState

data class EditDeckUIState(
    val deckId: String = "",
    val deckTitle: String = "",
    val title: InputFieldState = InputFieldState(),
    val visibility: String = "PUBLIC",
    val maxMembers: InputFieldState = InputFieldState(),
    val isLoading: Boolean = false,
    val generalErrorMessage: String? = null
)
