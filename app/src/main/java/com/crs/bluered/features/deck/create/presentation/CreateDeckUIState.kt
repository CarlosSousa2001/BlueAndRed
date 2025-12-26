package com.crs.bluered.features.deck.create.presentation

import com.crs.bluered.core.domain.model.InputFieldState
import com.crs.bluered.shared.domain.enums.DeckVisibility

data class CreateDeckUIState(
    val title: InputFieldState = InputFieldState(),
    val visibility: String = DeckVisibility.PUBLIC.toString(),
    val maxMembers: InputFieldState = InputFieldState(),
    val isLoading: Boolean = false,
    val isSuccess : Boolean = false,
    val generalErrorMessage: String? = null
)
