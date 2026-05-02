package com.crs.bluered.features.card.create.presentation

import androidx.lifecycle.ViewModel
import com.crs.bluered.core.utils.validation.FieldErrors
import com.crs.bluered.features.card.create.domain.usecase.CreateCardUseCase
import com.crs.bluered.features.card.create.domain.usecase.ValidateCreateDeckInputUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CreateCardViewModel @Inject constructor(
    private val createCardUseCase: CreateCardUseCase,
    private val validateCreateCardInputUseCase: ValidateCreateDeckInputUseCase
): ViewModel(){

    private val _uiState = MutableStateFlow(CreateCardUIState())
    val uiState: StateFlow<CreateCardUIState> = _uiState.asStateFlow()

    fun onEvent(event: CreateCardEvent){}

    fun onSubmit(){}

    private fun applyError(errors: FieldErrors) {

    }
}