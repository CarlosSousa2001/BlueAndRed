package com.crs.bluered.core.data.remote.response

import com.crs.bluered.core.data.local.datastore.DataStoreLocalDataSource
import com.crs.bluered.core.session.SessionManager
import com.crs.bluered.core.utils.DispatchersProvider
import com.crs.bluered.core.utils.logging.logInfo
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AccessTokenInterceptor @Inject constructor(
    private val localDataSource: DataStoreLocalDataSource,
    private val dispatchersProvider: DispatchersProvider,
    private val sessionManager: SessionManager
) : Interceptor {

    companion object {
        const val TOKEN_TYPE = "Bearer"
        const val HEADER_AUTHORIZATION = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val data = try {
            runBlocking(dispatchersProvider.io()) {
                localDataSource.getData().firstOrNull()
            }
        } catch (e: Exception) {
            logInfo("INTERCEPTOR", "Error getting data from datastore")
            null
        }

        val requestBuilder = chain.request().newBuilder()

        val token = data?.token.orEmpty()
        if (token.isNotBlank()) {
            requestBuilder.addHeader(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token")
        }

        val response = chain.proceed(requestBuilder.build())

        if (response.code == 401) {
            runBlocking(dispatchersProvider.io()) {
                sessionManager.onUnauthorized()
            }
        }

        return response
    }
}