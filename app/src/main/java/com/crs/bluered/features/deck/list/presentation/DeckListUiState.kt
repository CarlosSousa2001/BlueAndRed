package com.crs.bluered.features.deck.list.presentation

import com.crs.bluered.core.domain.model.PaginationMeta
import com.crs.bluered.features.deck.list.domain.model.DeckListItem

data class DeckListUiState(
    val items: List<DeckListItem> = emptyList(),

    val page: Int = 1,
    val perPage: Int = 10,
    val visibility: String? = null,
    val isOfficial: Boolean? = null,

    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,

    val meta: PaginationMeta? = null,

    val errorMessage: String? = null
) {
    val canLoadMore: Boolean
        get() = meta?.nextPage != null && !isLoadingMore && !isLoading
}