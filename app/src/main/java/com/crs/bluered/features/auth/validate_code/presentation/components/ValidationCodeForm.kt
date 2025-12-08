package com.crs.bluered.features.auth.validate_code.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.crs.bluered.core.domain.model.InputFieldState
import com.crs.bluered.ui.components.BRButton
import com.crs.bluered.ui.components.ButtonStyle
import com.crs.bluered.ui.components.RBOtpCodeInput
import com.crs.bluered.ui.theme.BlueRedTheme
import com.crs.bluered.ui.theme.BlueRedThemeSizing.sizing

@Composable
fun ValidationCodeForm(
    modifier: Modifier = Modifier,
    code: InputFieldState,
    isLoading: Boolean,
    generalErrorMessage: String?,

    onCodeChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onResendCode: () -> Unit,
) {
    Column(
        modifier = modifier.padding(sizing.md),
        verticalArrangement = Arrangement.spacedBy(sizing.sm)
    ) {
        Text(
            text = "Validar código",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
        )

        Text(
            text = "Insira o código de 6 dígitos enviado para seu e-mail.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        // OTP
        RBOtpCodeInput(
            modifier = Modifier.fillMaxWidth(),
            length = 6,
            value = code.value,
            onValueChange = onCodeChange,
            onComplete = { onSubmit() }
        )

        // Erro específico do código (se tiver)
        if (!code.errorMessage.isNullOrEmpty()) {
            Text(
                text = code.errorMessage.orEmpty(),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        // Erro geral (ex: código expirado, erro do backend)
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
            text = if (isLoading) "Validando..." else "Validar código",
            onClick = onSubmit,
            enabled = !isLoading,
            style = ButtonStyle.Primary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = sizing.sm)
        )

        // Reenviar código
        Text(
            text = "Não recebeu o código? Reenviar",
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .align(Alignment.End)
                .clickable { onResendCode() }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ValidationCodeFormPreview() {
    BlueRedTheme {
        ValidationCodeForm(
            code = InputFieldState(
                value = "12",
                errorMessage = "Código inválido"
            ),
            isLoading = false,
            generalErrorMessage = "O código expirou. Tente novamente.",
            onCodeChange = {},
            onSubmit = {},
            onResendCode = {}
        )
    }
}
