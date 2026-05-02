package com.crs.bluered.core.utils

import android.util.Patterns

// Helpers centralizados para validação de inputs.
// Estou usando esse padrão como alternativa a um "schema" de validação,
object InputValidator {

    fun validateEmail(email: String): String? {
        if (email.isBlank()) {
            return Constants.EMPTY_FIELD
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Constants.INVALID_EMAIL
        }
        return null
    }

    fun validatePassword(password: String): String? {
        if (password.isBlank()) {
            return Constants.EMPTY_FIELD
        }
        if (password.length < 6) {
            return Constants.PASSWORD_TOO_SHORT
        }
        if (!password.any { it.isUpperCase() }) {
            return Constants.PASSWORD_UPPERCASE_MISSING
        }
        val specialCharRegex = Regex("[^a-zA-Z0-9]")
        if (!password.contains(specialCharRegex)) {
            return Constants.PASSWORD_SPECIAL_CHAR_MISSING
        }
        if (!password.any { it.isDigit() }) {
            return Constants.PASSWORD_NUMBER_MISSING
        }

        return null
    }

    fun validateRequired(value: String): String? {
        if (value.isBlank()) {
            return Constants.EMPTY_FIELD
        }
        return null
    }

    fun validateMinLength(
        value: String,
        min: Int,
        message: String = "Mínimo de $min caracteres"
    ): String? {
        return if (value.trim().length < min) message else null
    }

    fun validateMaxLength(
        value: String,
        max: Int,
        message: String = "Máximo de $max caracteres"
    ): String? {
        return if (value.trim().length > max) message else null
    }

    fun validateLengthRange(
        value: String,
        min: Int,
        max: Int,
        minMessage: String = "Mínimo de $min caracteres",
        maxMessage: String = "Máximo de $max caracteres"
    ): String? {
        validateMinLength(value, min, minMessage)?.let { return it }
        validateMaxLength(value, max, maxMessage)?.let { return it }
        return null
    }

}