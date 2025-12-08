package com.crs.bluered.features.auth.register.domain.repository

import com.crs.bluered.features.auth.register.domain.model.RegisterRequestModel

interface RegisterRepository {
    suspend fun register(registerRequestModel: RegisterRequestModel) : Boolean
}