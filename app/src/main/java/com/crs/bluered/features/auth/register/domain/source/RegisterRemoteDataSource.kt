package com.crs.bluered.features.auth.register.domain.source

import com.crs.bluered.features.auth.register.domain.model.RegisterRequestModel

interface RegisterRemoteDataSource {
    suspend fun register(registerRequestModel: RegisterRequestModel) : Boolean
}