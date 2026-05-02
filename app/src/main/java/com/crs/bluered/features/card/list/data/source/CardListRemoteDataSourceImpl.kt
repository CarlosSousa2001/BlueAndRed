package com.crs.bluered.features.card.list.data.source

import com.crs.bluered.core.data.mappers.toDomain
import com.crs.bluered.core.data.remote.response.PaginatedResponseDto
import com.crs.bluered.core.domain.model.PaginatedResponse
import com.crs.bluered.core.utils.ServiceResult
import com.crs.bluered.features.card.list.domain.source.CardListRemoteDataSource
import com.crs.bluered.shared.data.dto.CardResponseDto
import com.crs.bluered.shared.data.mappers.toDomain
import com.crs.bluered.shared.domain.entities.CardResponseEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class CardListRemoteDataSourceImpl @Inject constructor(
    private val client: HttpClient
) : CardListRemoteDataSource {
    override suspend fun list(params: CardListRemoteDataSource.Params): ServiceResult<PaginatedResponse<CardResponseEntity>> {
        return try {
            val response = client.get("decks/${params.deckId}/cards") {
                url {
                    parameters.append("page", params.page.toString())
                    parameters.append("perPage", params.perPage.toString())
                }
            }
            if (response.status.value in 200..299) {
                val dto = response.body<PaginatedResponseDto<CardResponseDto>>()
                ServiceResult.Success(dto.toDomain { it.toDomain() })
            } else {
                ServiceResult.Error(
                    code = response.status.value.toString(),
                    message = "Erro ao listar cards (HTTP ${response.status.value})"
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
