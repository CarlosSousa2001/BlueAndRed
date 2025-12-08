package com.crs.bluered.features.auth.register.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crs.bluered.core.domain.exceptions.LoginException
import com.crs.bluered.core.utils.validation.FieldErrors
import com.crs.bluered.features.auth.register.domain.model.RegisterRequestModel
import com.crs.bluered.features.auth.register.domain.usecase.RegisterUseCase
import com.crs.bluered.features.auth.register.domain.usecase.ValidateRegisterInputUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val validateRegisterUseCase: ValidateRegisterInputUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(value = RegisterUIState())
    val uiState: StateFlow<RegisterUIState> = _uiState.asStateFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.UsernameChanged -> {
                _uiState.update { state ->
                    state.copy(
                        username = state.username.copy(
                            value = event.value,
                            errorMessage = null
                        ),
                        generalErrorMessage = null
                    )
                }
            }

            is RegisterEvent.EmailChanged -> {
                _uiState.update { state ->
                    state.copy(
                        email = state.email.copy(
                            value = event.value,
                            errorMessage = null
                        ),
                        generalErrorMessage = null
                    )
                }
            }

            is RegisterEvent.PasswordChanged -> {
                _uiState.update { state ->
                    state.copy(
                        password = state.password.copy(
                            value = event.value,
                            errorMessage = null
                        ),
                        generalErrorMessage = null
                    )
                }
            }

            is RegisterEvent.TogglePasswordVisibility -> {
                _uiState.update { state ->
                    state.copy(
                        isPasswordShown = !state.isPasswordShown
                    )
                }
            }

            is RegisterEvent.Submit -> {
                submit()
            }
        }
    }

    private fun submit() {

        val usernameValue = _uiState.value.username.value
        val emailValue = _uiState.value.email.value
        val passwordValue = _uiState.value.password.value

        val errors = validateRegisterUseCase(
            username = usernameValue,
            email = emailValue,
            password = passwordValue
        )

        if (errors.isNotEmpty()) {
            applyError(errors)
            return
        }

        _uiState.update { it.copy(isLoading = true, generalErrorMessage = null) }

        viewModelScope.launch {
            try {
                val response = registerUseCase(
                    registerRequestModel = RegisterRequestModel(
                        username = usernameValue,
                        email = emailValue,
                        password = passwordValue
                    )
                )

                if (!response) {
                    _uiState.update {
                        it.copy(
                            isSuccess = false,
                            isLoading = false,
                            generalErrorMessage = "Falha ao criar usuário."
                        )
                    }
                    return@launch
                }

                _uiState.update {
                    it.copy(
                        isSuccess = true,
                        isLoading = false,
                        generalErrorMessage = null
                    )
                }

            } catch (e: LoginException) {
                _uiState.update {
                    it.copy(
                        isSuccess = false,
                        isLoading = false,
                        generalErrorMessage = e.message
                    )
                }
            }
        }

    }

    private fun applyError(errors: FieldErrors) {
        _uiState.update { state ->
            state.copy(
                username = state.username.copy(errorMessage = errors["username"]),
                email = state.email.copy(errorMessage = errors["email"]),
                password = state.password.copy(errorMessage = errors["password"]),
            )
        }
    }

}