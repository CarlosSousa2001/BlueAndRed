package com.crs.bluered.features.auth.register.data.source

import com.crs.bluered.features.auth.register.data.model.mappers.toRegisterRequestModelDto
import com.crs.bluered.features.auth.register.domain.model.RegisterRequestModel
import com.crs.bluered.features.auth.register.domain.source.RegisterRemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import javax.inject.Inject

class RegisterRemoteDataSourceImpl @Inject constructor(
    private val client: HttpClient
) : RegisterRemoteDataSource {
    override suspend fun register(registerRequestModel: RegisterRequestModel) : Boolean {
        val response = client.post(urlString = "auth/sign-up") {
            contentType(ContentType.Application.Json)
            setBody(registerRequestModel.toRegisterRequestModelDto())
        }
        return response.status == HttpStatusCode.Created
    }
}