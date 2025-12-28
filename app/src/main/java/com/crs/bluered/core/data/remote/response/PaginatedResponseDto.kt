package com.crs.bluered.core.data.remote.response
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class PaginatedResponseDto<T>(
    @SerialName("data")
    val data: List<T>,
    @SerialName("meta")
    val meta: PaginationMetaDto
)

@Serializable
data class PaginationMetaDto(
    @SerialName("totalItems")
    val totalItems: Int,
    @SerialName("perPage")
    val perPage: Int,
    @SerialName("currentPage")
    val currentPage: Int,
    @SerialName("totalPages")
    val totalPages: Int,
    @SerialName("previousPage")
    val previousPage: Int? = null,
    @SerialName("nextPage")
    val nextPage: Int? = null
)
