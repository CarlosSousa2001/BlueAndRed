package com.crs.bluered.core.utils

import android.util.Patterns

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

}