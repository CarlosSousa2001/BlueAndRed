package com.crs.bluered.features.card.list.presentation

interface CardListEvent {
    data object LoadIfNeeded : CardListEvent
    data object Refresh : CardListEvent
    data object LoadMore : CardListEvent
}
