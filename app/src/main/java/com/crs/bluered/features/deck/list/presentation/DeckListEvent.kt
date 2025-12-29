package com.crs.bluered.features.deck.list.presentation

import com.crs.bluered.shared.domain.enums.DeckListScope

interface DeckListEvent {
    data object LoadIfNeeded : DeckListEvent
    data object Refresh : DeckListEvent
    data object LoadMore : DeckListEvent
    data object ClearError : DeckListEvent

    data class ChangeScope(val value: DeckListScope) : DeckListEvent
}