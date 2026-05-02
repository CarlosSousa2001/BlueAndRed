package com.crs.bluered.features.card.list.presentation

import com.crs.bluered.core.domain.model.PaginationMeta
import com.crs.bluered.shared.domain.entities.CardResponseEntity

data class CardListUiState(
    val deckId: String = "",
    val deckTitle: String = "",
    val canEdit: Boolean = false,
    val visibility: String = "PUBLIC",
    val maxMembers: Int? = null,
    val items: List<CardResponseEntity> = emptyList(),
    val page: Int = 1,
    val perPage: Int = 10,
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val meta: PaginationMeta? = null,
    val errorMessage: String? = null
) {
    val canLoadMore: Boolean
        get() = meta?.nextPage != null && !isLoading && !isLoadingMore
}
