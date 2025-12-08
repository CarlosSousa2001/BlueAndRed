package com.crs.bluered.features.auth.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crs.bluered.core.domain.exceptions.LoginException
import com.crs.bluered.core.utils.logging.logInfo
import com.crs.bluered.core.utils.validation.FieldErrors
import com.crs.bluered.features.auth.login.domain.model.AuthRequestModel
import com.crs.bluered.features.auth.login.domain.usecase.LoginUseCase
import com.crs.bluered.features.auth.login.domain.usecase.SaveLocalStorageTokenUserData
import com.crs.bluered.features.auth.login.domain.usecase.ValidateLoginInputUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateLoginInputUseCase: ValidateLoginInputUseCase,
    private val loginUseCase: LoginUseCase,
    private val saveLocalStorageTokenUserDataUseCase: SaveLocalStorageTokenUserData
) : ViewModel() {

    private val _uiState = MutableStateFlow(value = LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
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

            is LoginEvent.PasswordChanged -> {
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

            LoginEvent.TogglePasswordVisibility -> {
                _uiState.update { state ->
                    state.copy(
                        isPasswordShown = !state.isPasswordShown
                    )
                }
            }

            LoginEvent.Submit -> {
                submit()
            }
        }
    }

    private fun submit() {

        val emailValue = _uiState.value.email.value
        val passwordValue = _uiState.value.password.value

        val errors = validateLoginInputUseCase(emailValue, passwordValue)

        if (errors.isNotEmpty()) {
            applyErrors(errors)
            return
        }

        _uiState.update { it.copy(isLoading = true, generalErrorMessage = null) }

        viewModelScope.launch {
            try {
                val response = loginUseCase(
                    authRequestModel = AuthRequestModel(
                        email = emailValue,
                        password = passwordValue
                    )
                )

                logInfo("HTTP_RESPONSE", response.toString())

                val token = response.token
                val username = response.username

                if (token.isNullOrBlank() || username.isNullOrBlank()) {
                    _uiState.update {
                        it.copy(
                            isSuccess = false,
                            isLoading = false,
                            generalErrorMessage = "Falha ao fazer login."
                        )
                    }
                    return@launch
                }

                saveLocalStorageTokenUserDataUseCase(
                    token = token,
                    username = username
                )

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

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isSuccess = false,
                        isLoading = false,
                        generalErrorMessage = "Erro inesperado. Tente novamente."
                    )
                }
            }
        }
    }

    private fun applyErrors(errors: FieldErrors) {
        _uiState.update { state ->
            state.copy(
                email = state.email.copy(errorMessage = errors["email"]),
                password = state.password.copy(errorMessage = errors["password"]),
            )
        }
    }
}