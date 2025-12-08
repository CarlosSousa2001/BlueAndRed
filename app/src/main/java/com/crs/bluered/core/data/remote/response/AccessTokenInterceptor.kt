package com.crs.bluered.core.data.remote.response

import com.crs.bluered.core.data.local.datastore.DataStoreLocalDataSource
import com.crs.bluered.core.utils.DispatchersProvider
import com.crs.bluered.core.utils.logging.logInfo
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AccessTokenInterceptor @Inject constructor(
    private val localDataSource: DataStoreLocalDataSource,
    private val dispatchersProvider: DispatchersProvider
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

        val request = chain.request().newBuilder()

        data?.token.let { token ->
            request.addHeader(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token")
        } ?: run {
            logInfo("INTERCEPTOR", "Token is null")
        }
        return chain.proceed(request.build())
    }
}