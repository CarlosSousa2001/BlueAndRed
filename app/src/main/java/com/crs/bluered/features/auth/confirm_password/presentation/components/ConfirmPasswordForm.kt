package com.crs.bluered.features.auth.confirm_password.presentation.components

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
fun ConfirmPasswordForm(
    modifier: Modifier = Modifier,
    password: InputFieldState,
    confirmPassword: InputFieldState,
    isPasswordShown: Boolean,
    isConfirmPasswordShown: Boolean,
    isLoading: Boolean,
    generalErrorMessage: String?,

    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onTogglePasswordVisibility: () -> Unit,
    onToggleConfirmPasswordVisibility: () -> Unit,
    onSubmit: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier.padding(sizing.md),
        verticalArrangement = Arrangement.spacedBy(sizing.sm)
    ) {

        Text(
            text = "Criar nova senha",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
        )

        Text(
            text = "Digite sua nova senha e confirme abaixo.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        // NEW PASSWORD
        RBOutlineTextField(
            label = "Nova senha",
            value = password.value,
            valueChange = onPasswordChange,
            isError = password.errorMessage != null,
            errorMessage = password.errorMessage,
            leadingIcon = painterResource(id = R.drawable.ic_encrypted_24),
            trailingIcon = painterResource(
                id = if (isPasswordShown) R.drawable.ic_visibility_off_24 else R.drawable.ic_visibility_24
            ),
            onTrailingIconClick = onTogglePasswordVisibility,
            visualTransformation = if (isPasswordShown) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        // CONFIRM PASSWORD
        RBOutlineTextField(
            label = "Confirmar senha",
            value = confirmPassword.value,
            valueChange = onConfirmPasswordChange,
            isError = confirmPassword.errorMessage != null,
            errorMessage = confirmPassword.errorMessage,
            leadingIcon = painterResource(id = R.drawable.ic_encrypted_24),
            trailingIcon = painterResource(
                id = if (isConfirmPasswordShown) R.drawable.ic_visibility_off_24 else R.drawable.ic_visibility_24
            ),
            onTrailingIconClick = onToggleConfirmPasswordVisibility,
            visualTransformation = if (isConfirmPasswordShown) VisualTransformation.None else PasswordVisualTransformation(),
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
            text = if (isLoading) "Salvando..." else "Salvar nova senha",
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
private fun ConfirmPasswordFormPreview() {
    BlueRedTheme {
        ConfirmPasswordForm(
            password = InputFieldState(errorMessage = "Senha inválida"),
            confirmPassword = InputFieldState(errorMessage = "Senhas não coincidem"),
            isPasswordShown = false,
            isConfirmPasswordShown = false,
            isLoading = false,
            generalErrorMessage = "Erro inesperado",

            onPasswordChange = {},
            onConfirmPasswordChange = {},
            onTogglePasswordVisibility = {},
            onToggleConfirmPasswordVisibility = {},
            onSubmit = {}
        )
    }
}