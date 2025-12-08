package com.crs.bluered.features.auth.forgot_password.presentation.components

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
fun ForgotPasswordForm(
    modifier: Modifier = Modifier,
    email: InputFieldState,
    isLoading: Boolean,
    generalErrorMessage: String?,
    onEmailChange: (String) -> Unit,
    onSubmit: () -> Unit,
) {

    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier.padding(sizing.md),
        verticalArrangement = Arrangement.spacedBy(sizing.sm)
    ) {

        Text(
            text = "Entre com seu E-mail",
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
            text = if (isLoading) "Enviando..." else "Enviar",
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
private fun ForgotPasswordFormPreview() {
    BlueRedTheme {
        ForgotPasswordForm(
            email = InputFieldState(
                value = "",
                errorMessage = "Email inválido"
            ),
            isLoading = false,
            generalErrorMessage = "Preencha todos os campos",
            onEmailChange = {},
            onSubmit = {}
        )
    }
}