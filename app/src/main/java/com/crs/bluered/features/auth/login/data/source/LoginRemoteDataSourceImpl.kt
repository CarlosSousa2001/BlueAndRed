package com.crs.bluered.features.auth.login.data.source

import com.crs.bluered.features.auth.login.data.model.dto.TokenResponseModelDto
import com.crs.bluered.features.auth.login.data.model.mappers.toAuthRequestDto
import com.crs.bluered.features.auth.login.data.model.mappers.toTokenResponseModel
import com.crs.bluered.features.auth.login.domain.model.AuthRequestModel
import com.crs.bluered.features.auth.login.domain.model.TokenResponseModel
import com.crs.bluered.features.auth.login.domain.source.LoginRemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class LoginRemoteDataSourceImpl @Inject constructor(
    private val client: HttpClient
) : LoginRemoteDataSource {

    override suspend fun login(authRequestModel: AuthRequestModel): TokenResponseModel {
        val response = client.post(urlString = "auth/sign-in") {
            contentType(ContentType.Application.Json)
            setBody(authRequestModel.toAuthRequestDto())
        }
        val result = response.body<TokenResponseModelDto>()
        return result.toTokenResponseModel()
    }
}