package com.crs.bluered.features.deck.list.data.source

import com.crs.bluered.core.data.mappers.toDomain
import com.crs.bluered.core.data.remote.response.PaginatedResponseDto
import com.crs.bluered.core.domain.model.PaginatedResponse
import com.crs.bluered.core.utils.ServiceResult
import com.crs.bluered.features.deck.list.data.model.dto.DeckListItemDto
import com.crs.bluered.features.deck.list.data.model.mappers.toDomain
import com.crs.bluered.features.deck.list.domain.model.DeckListItem
import com.crs.bluered.features.deck.list.domain.source.DeckListRemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class DeckListRemoteDataSourceImpl @Inject constructor(
    private val client: HttpClient
) : DeckListRemoteDataSource {

    override suspend fun list(
        params: DeckListRemoteDataSource.Params
    ): ServiceResult<PaginatedResponse<DeckListItem>> {
        return try {
            val response = client.get("decks/me") {
                url {
                    parameters.append("page", params.page.toString())
                    parameters.append("perPage", params.perPage.toString())
                    params.visibility?.let { parameters.append("visibility", it) }
                    params.isOfficial?.let { parameters.append("isOfficial", it.toString()) }
                }
            }

            if (response.status.value in 200..299) {
                val dto = response.body<PaginatedResponseDto<DeckListItemDto>>()
                ServiceResult.Success(dto.toDomain { it.toDomain() })
            } else {
                ServiceResult.Error(
                    code = response.status.value.toString(),
                    message = "Erro ao listar decks (HTTP ${response.status.value})"
                )
            }
        } catch (t: Throwable) {
            ServiceResult.Error(
                code = null,
                message = t.message ?: "Erro inesperado"
            )
        }
    }
}