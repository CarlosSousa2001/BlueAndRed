package com.crs.bluered.core.utils.extensions

import com.crs.bluered.core.utils.ResponseData
import kotlinx.coroutines.flow.Flow

suspend fun <T> Flow<ResponseData<T>>.observerState(
    onLoading: suspend () -> Unit,
    onEmpty: suspend () -> Unit = {},
    onSuccess: suspend (data: T) -> Unit,
    onFailure: suspend (error: Throwable) -> Unit
){
    collect { response ->
        when (response) {
            is ResponseData.Loading -> onLoading.invoke()
            is ResponseData.Empty -> onEmpty.invoke()
            is ResponseData.Success -> onSuccess.invoke(response.data)
            is ResponseData.Error -> onFailure.invoke(response.throwable)
        }
    }
}