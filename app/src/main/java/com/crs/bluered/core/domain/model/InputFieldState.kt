package com.crs.bluered.core.domain.model

data class InputFieldState(
    val value: String = "",
    val errorMessage: String? = null,
) {
    val isError: Boolean get() = errorMessage != null
}