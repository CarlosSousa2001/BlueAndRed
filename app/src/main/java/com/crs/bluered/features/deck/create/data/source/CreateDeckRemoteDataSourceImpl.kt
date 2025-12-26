package com.crs.bluered.features.deck.create.data.source

import com.crs.bluered.core.utils.ServiceResult
import com.crs.bluered.features.deck.create.data.model.mappers.toCreateDeckModelDto
import com.crs.bluered.features.deck.create.domain.model.CreateDeckModel
import com.crs.bluered.features.deck.create.domain.source.CreateDeckRemoteDataSource
import com.crs.bluered.shared.domain.entities.DeckResponseEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class CreateDeckRemoteDataSourceImpl @Inject constructor(
    private val client: HttpClient
) : CreateDeckRemoteDataSource {
    override suspend fun create(input: CreateDeckModel): ServiceResult<DeckResponseEntity> {
        return try {
            val response = client.post("deck/create") {
                contentType(ContentType.Application.Json)
                setBody(input.toCreateDeckModelDto())
            }

            if (response.status.value in 200..299) {
                ServiceResult.Success(response.body())
            } else {
                ServiceResult.Error(
                    code = response.status.value.toString(),
                    message = "Erro ao criar deck (HTTP ${response.status.value})"
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