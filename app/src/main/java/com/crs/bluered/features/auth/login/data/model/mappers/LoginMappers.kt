package com.crs.bluered.features.auth.login.data.model.mappers

import com.crs.bluered.features.auth.login.data.model.dto.AuthRequestModelDto
import com.crs.bluered.features.auth.login.data.model.dto.TokenResponseModelDto
import com.crs.bluered.features.auth.login.domain.model.AuthRequestModel
import com.crs.bluered.features.auth.login.domain.model.TokenResponseModel

fun AuthRequestModel.toAuthRequestDto(): AuthRequestModelDto{
    return AuthRequestModelDto(
        email = email,
        password = password
    )
}

fun TokenResponseModelDto.toTokenResponseModel(): TokenResponseModel {
    return TokenResponseModel(token = token, username = username)
}