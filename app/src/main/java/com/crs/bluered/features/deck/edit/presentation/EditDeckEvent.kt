package com.crs.bluered.features.deck.edit.presentation

interface EditDeckEvent {
    data class OnTitleChange(val value: String) : EditDeckEvent
    data class OnVisibilityChange(val value: String) : EditDeckEvent
    data class OnMaxMembersChange(val value: String) : EditDeckEvent
    data object OnSubmit : EditDeckEvent
}
