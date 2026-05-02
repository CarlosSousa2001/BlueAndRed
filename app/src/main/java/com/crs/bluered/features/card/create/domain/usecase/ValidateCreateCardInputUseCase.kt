package com.crs.bluered.features.card.create.domain.usecase

import com.crs.bluered.core.utils.InputValidator
import com.crs.bluered.core.utils.validation.FieldErrors

interface ValidateCreateDeckInputUseCase {
    operator fun invoke(
        label: String,
        title: String
    ): FieldErrors
}

class ValidateCreateDeckInputUseCaseImpl : ValidateCreateDeckInputUseCase {

    override fun invoke(
        label: String,
        title: String
    ): FieldErrors {
        val errors = mutableMapOf<String, String>()

        InputValidator.validateRequired(label)?.let { message ->
            errors["label"] = message
        }

        InputValidator.validateRequired(title)?.let { message ->
            errors["title"] = message
        }
        return errors
    }

}