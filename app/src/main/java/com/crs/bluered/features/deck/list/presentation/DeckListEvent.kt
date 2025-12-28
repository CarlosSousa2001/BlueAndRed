package com.crs.bluered.features.deck.list.presentation

interface DeckListEvent {
    data object LoadIfNeeded : DeckListEvent
    data object Refresh : DeckListEvent
    data object LoadMore : DeckListEvent
    data object ClearError : DeckListEvent

    data class ChangeVisibility(val value: String?) : DeckListEvent
    data class ChangeIsOfficial(val value: Boolean?) : DeckListEvent
}