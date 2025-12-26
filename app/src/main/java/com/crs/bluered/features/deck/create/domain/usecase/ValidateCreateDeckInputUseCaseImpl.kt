package com.crs.bluered.features.deck.create.domain.usecase

import com.crs.bluered.core.utils.InputValidator
import com.crs.bluered.core.utils.validation.FieldErrors

interface ValidateCreateDeckInputUseCase {
    operator fun invoke(
        title: String,
        visibility: String,
        maxMembers: String
    ): FieldErrors
}

class ValidateCreateDeckInputUseCaseImpl : ValidateCreateDeckInputUseCase {
    override fun invoke(
        title: String,
        visibility: String,
        maxMembers: String
    ): FieldErrors {
        val errors = mutableMapOf<String, String>()

        InputValidator.validateRequired(title)?.let { message ->
            errors["title"] = message
        }

        InputValidator.validateMinLength(
            value = maxMembers,
            min = 1
        )?.let { message ->
            errors["maxMembers"] = message
        }

        InputValidator.validateMaxLength(
            value = maxMembers,
            max = 100
        )?.let { message ->
            errors["maxMembers"] = message
        }
        return errors
    }
}