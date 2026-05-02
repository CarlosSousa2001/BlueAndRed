package com.crs.bluered.core.utils

sealed class ServiceResult<out T> {
    data class Success<out T>(val data: T) : ServiceResult<T>()
    data class Error(val code: String? = null, val message: String? = null): ServiceResult<Nothing>()
}