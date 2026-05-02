package com.crs.bluered.core.data.mappers

import com.crs.bluered.core.data.remote.response.PaginatedResponseDto
import com.crs.bluered.core.data.remote.response.PaginationMetaDto
import com.crs.bluered.core.domain.model.PaginatedResponse
import com.crs.bluered.core.domain.model.PaginationMeta

fun PaginationMetaDto.toDomain(): PaginationMeta {
    return PaginationMeta(
        totalItems = totalItems,
        perPage = perPage,
        currentPage = currentPage,
        totalPages = totalPages,
        previousPage = previousPage,
        nextPage = nextPage
    )
}

fun <TDto, TDomain> PaginatedResponseDto<TDto>.toDomain(
    itemMapper: (TDto) -> TDomain
): PaginatedResponse<TDomain> {
    return PaginatedResponse(
        data = data.map(itemMapper),
        meta = meta.toDomain()
    )
}