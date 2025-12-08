package com.crs.bluered.features.auth.register.domain.usecase

import com.crs.bluered.core.utils.InputValidator
import com.crs.bluered.core.utils.validation.FieldErrors

interface ValidateRegisterInputUseCase {
    operator fun invoke(username: String, email: String, password: String): FieldErrors
}

class ValidateRegisterInputUseCaseImpl : ValidateRegisterInputUseCase {
    override fun invoke(
        username: String,
        email: String,
        password: String
    ): FieldErrors {
        val errors = mutableMapOf<String, String>()

        InputValidator.validateRequired(username)?.let { message ->
            errors["username"] = message
        }

        InputValidator.validateEmail(email)?.let { message ->
            errors["email"] = message
        }

        InputValidator.validatePassword(password)?.let { message ->
            errors["password"] = message
        }

        return errors
    }

}