package com.crs.bluered.features.deck.create.presentation

interface CreateDeckEvent {
    data class OnTitleChange(val value: String) : CreateDeckEvent
    data class OnVisibilityChange(val value: String) : CreateDeckEvent
    data class OnMaxMembersChange(val value: String) : CreateDeckEvent
    data object OnSubmit : CreateDeckEvent
}