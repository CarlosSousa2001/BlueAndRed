package com.crs.bluered.features.deck.list.presentation

import com.crs.bluered.core.domain.model.PaginationMeta
import com.crs.bluered.features.deck.list.domain.model.DeckListItem
import com.crs.bluered.shared.domain.enums.DeckListScope

data class DeckListUiState(
    val items: List<DeckListItem> = emptyList(),

    val page: Int = 1,
    val perPage: Int = 10,

    val scope: DeckListScope = DeckListScope.GLOBAL,

    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,

    val meta: PaginationMeta? = null,

    val errorMessage: String? = null
) {
    val canLoadMore: Boolean
        get() = meta?.nextPage != null && !isLoadingMore && !isLoading
}