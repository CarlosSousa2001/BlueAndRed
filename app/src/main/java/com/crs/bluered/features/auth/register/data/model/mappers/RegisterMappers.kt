package com.crs.bluered.features.auth.register.data.model.mappers

import com.crs.bluered.features.auth.register.data.model.dto.RegisterRequestModelDto
import com.crs.bluered.features.auth.register.domain.model.RegisterRequestModel

fun RegisterRequestModel.toRegisterRequestModelDto(): RegisterRequestModelDto {
    return RegisterRequestModelDto(
        username = username,
        email = email,
        password = password
    )
}