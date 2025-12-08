package com.crs.bluered.features.auth.register.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.crs.bluered.R
import com.crs.bluered.core.domain.model.InputFieldState
import com.crs.bluered.ui.components.BRButton
import com.crs.bluered.ui.components.ButtonStyle
import com.crs.bluered.ui.components.RBOutlineTextField
import com.crs.bluered.ui.theme.BlueRedTheme
import com.crs.bluered.ui.theme.BlueRedThemeSizing.sizing


@Composable
fun RegisterForm(
    modifier: Modifier = Modifier,
    username: InputFieldState,
    email: InputFieldState,
    password: InputFieldState,
    isPasswordShown: Boolean,
    isLoading: Boolean,
    generalErrorMessage: String?,

    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onTogglePasswordVisibility: () -> Unit,
    onSubmit: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier.padding(sizing.md),
        verticalArrangement = Arrangement.spacedBy(sizing.sm)
    ){
        Text(
            text = "Registrar",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
        )

        RBOutlineTextField(
            label = "Nome de usuário",
            value = username.value,
            valueChange = onUsernameChange,
            isError = !username.errorMessage.isNullOrEmpty(),
            errorMessage = email.errorMessage,
            maxLength = 45,
            leadingIcon = painterResource(R.drawable.ic_person_24),
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        RBOutlineTextField(
            label = "Email",
            value = email.value,
            valueChange = onEmailChange,
            isError = !email.errorMessage.isNullOrEmpty(),
            errorMessage = email.errorMessage,
            leadingIcon = painterResource(id = R.drawable.ic_person_24),
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        RBOutlineTextField(
            label = "Senha",
            value = password.value,
            valueChange = onPasswordChange,
            isError = !password.errorMessage.isNullOrEmpty(),
            errorMessage = password.errorMessage,
            leadingIcon = painterResource(id = R.drawable.ic_encrypted_24),
            trailingIcon = painterResource(
                id = if (isPasswordShown) {
                    R.drawable.ic_visibility_off_24
                } else {
                    R.drawable.ic_visibility_24
                }
            ),
            onTrailingIconClick = onTogglePasswordVisibility,
            visualTransformation = if (isPasswordShown) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    onSubmit()
                }
            )
        )

        if (!generalErrorMessage.isNullOrEmpty()) {
            Text(
                text = generalErrorMessage,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = sizing.xs)
            )
        }

        BRButton(
            text = if (isLoading) "Registrando..." else "Registrar",
            onClick = onSubmit,
            enabled = !isLoading,
            style = ButtonStyle.Primary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = sizing.sm)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterFormPreview() {
    BlueRedTheme {
        RegisterForm(
            username = InputFieldState(
                value = "",
                errorMessage = "Username inválido"
            ),
            email = InputFieldState(
                value = "",
                errorMessage = "Email inválido"
            ),
            password = InputFieldState(
                value = "",
                errorMessage = null
            ),
            isPasswordShown = false,
            isLoading = false,
            generalErrorMessage = "Preencha todos os campos",

            onUsernameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onTogglePasswordVisibility = {},
            onSubmit = {},
        )
    }
}