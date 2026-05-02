package com.crs.bluered.core.utils

sealed class ResponseData<out T> {
    data object Empty : ResponseData<Nothing>()
    data object Loading : ResponseData<Nothing>()
    data class Success<out T>(val data: T) : ResponseData<T>()
    data class Error(val throwable: Throwable) : ResponseData<Nothing>()
}