package com.crs.bluered.features.auth.login.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun LoginForm(
    modifier: Modifier = Modifier,

    email: InputFieldState,
    password: InputFieldState,
    isPasswordShown: Boolean,
    isLoading: Boolean,
    generalErrorMessage: String?,

    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onTogglePasswordVisibility: () -> Unit,
    onSubmit: () -> Unit,
    onLoginWithGoogle: () -> Unit = {},
    onForgotPassword: () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier.padding(sizing.md),
        verticalArrangement = Arrangement.spacedBy(sizing.sm)
    ) {

        Text(
            text = "Entrar",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
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

        Text(
            text = "Esqueceu a senha?",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier
                .align(Alignment.End)
                .padding(start = sizing.xs)
                .clickable { onForgotPassword() }
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

        // BOTÃO LOGIN
        BRButton(
            text = if (isLoading) "Entrando..." else "Entrar",
            onClick = onSubmit,
            enabled = !isLoading,
            style = ButtonStyle.Primary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = sizing.sm)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = sizing.sm),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(

                modifier = Modifier.weight(1f)
            )

            Text(
                text = "Ou",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = sizing.sm)
            )

            HorizontalDivider(
                modifier = Modifier.weight(1f)
            )
        }

        // BOTÃO LOGIN COM GOOGLE
        BRButton(
            text = "Entrar com Google",
            onClick = onLoginWithGoogle,
            enabled = !isLoading,
            style = ButtonStyle.Danger,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_google_24),
                    contentDescription = "Login com Google"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginFormPreview() {
    BlueRedTheme {
        LoginForm(
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

            onEmailChange = {},
            onPasswordChange = {},
            onTogglePasswordVisibility = {},
            onSubmit = {}
        )
    }
}
