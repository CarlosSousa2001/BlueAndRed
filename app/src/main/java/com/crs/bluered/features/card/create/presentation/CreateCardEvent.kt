package com.crs.bluered.features.card.create.presentation

interface CreateCardEvent {
    data class OnLabelChange(val value: String) : CreateCardEvent
    data class OnTextChange(val value: String) : CreateCardEvent
    data object OnSubmit : CreateCardEvent
}