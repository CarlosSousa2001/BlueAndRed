package com.crs.bluered.core.domain.model

data class PaginatedResponse<T>(
    val data: List<T>,
    val meta: PaginationMeta
)

data class PaginationMeta(
    val totalItems: Int,
    val perPage: Int,
    val currentPage: Int,
    val totalPages: Int,
    val previousPage: Int?,
    val nextPage: Int?
) {
    val hasPrevious: Boolean get() = previousPage != null
    val hasNext: Boolean get() = nextPage != null
}
