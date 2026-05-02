package com.crs.bluered.features.card.create.data.source

import com.crs.bluered.core.utils.ServiceResult
import com.crs.bluered.features.card.create.data.mappers.toDto
import com.crs.bluered.features.card.create.domain.model.CardRequest
import com.crs.bluered.features.card.create.domain.source.CreateCardRemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class CreateCardRemoteDataSourceImpl @Inject constructor(
    private val client: HttpClient
) : CreateCardRemoteDataSource {
    override suspend fun create(cards: CardRequest, deckId: String): ServiceResult<Unit> {
        return try {
            val response = client.post(urlString = "decks/$deckId/cards") {
                contentType(ContentType.Application.Json)
                setBody(cards.toDto())
            }

            if (response.status.value == 201) {
                ServiceResult.Success(Unit)
            } else {
                ServiceResult.Error(
                    code = response.status.value.toString(),
                    message = "Erro ao criar card (HTTP ${response.status.value})"
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
