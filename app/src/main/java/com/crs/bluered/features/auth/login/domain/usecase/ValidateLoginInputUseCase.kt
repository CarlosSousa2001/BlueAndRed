package com.crs.bluered.features.auth.login.domain.usecase

import com.crs.bluered.core.utils.InputValidator
import com.crs.bluered.core.utils.validation.FieldErrors
import javax.inject.Inject

interface ValidateLoginInputUseCase {
    operator fun invoke(email: String, password: String): FieldErrors
}

// Use case responsável por centralizar as validações do formulário de login.
// Ele chama os helpers do InputValidator e retorna um map com os erros encontrados.
class ValidateLoginInputUseCaseImpl @Inject constructor() : ValidateLoginInputUseCase {

    override fun invoke(email: String, password: String): FieldErrors {

        val errors = mutableMapOf<String, String>()

        InputValidator.validateEmail(email)?.let { message ->
            errors["email"] = message
        }

        InputValidator.validatePassword(password)?.let { message ->
            errors["password"] = message
        }

        return errors
    }
}
