package com.crs.bluered.features.auth.login.domain.source

import com.crs.bluered.features.auth.login.domain.model.AuthRequestModel
import com.crs.bluered.features.auth.login.domain.model.TokenResponseModel

interface LoginRemoteDataSource {
    suspend fun login(authRequestModel: AuthRequestModel): TokenResponseModel
}