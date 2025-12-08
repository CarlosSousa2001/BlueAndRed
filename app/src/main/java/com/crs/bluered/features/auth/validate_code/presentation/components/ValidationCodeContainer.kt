package com.crs.bluered.ui.features.auth.validation_code.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.crs.bluered.R
import com.crs.bluered.features.auth.validate_code.presentation.ValidationCodeEvent
import com.crs.bluered.features.auth.validate_code.presentation.ValidationCodeUIState
import com.crs.bluered.features.auth.validate_code.presentation.components.ValidationCodeForm
import com.crs.bluered.ui.theme.BlueRedTheme
import com.crs.bluered.ui.theme.BlueRedThemeSizing.sizing

@Composable
fun ValidationCodeContainer(
    uiState: ValidationCodeUIState,
    onEvent: (ValidationCodeEvent) -> Unit,
    paddingValues: PaddingValues,
    onNavigateBackToLogin: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(sizing.sm)
        ) {

            Image(
                painter = painterResource(id = R.drawable.bg_validation_code_img_foreground),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.33f)
            )

            ValidationCodeForm(
                modifier = Modifier.fillMaxWidth(),
                code = uiState.code,
                isLoading = uiState.isLoading,
                generalErrorMessage = uiState.generalErrorMessage,
                onCodeChange = { onEvent(ValidationCodeEvent.CodeChanged(it)) },
                onSubmit = { onEvent(ValidationCodeEvent.Submit) },
                onResendCode = { onEvent(ValidationCodeEvent.ResendCode) }
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Digitou o e-mail errado?",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Voltar para login",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.clickable { onNavigateBackToLogin() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ValidationCodeContainerPreview() {
    BlueRedTheme {
        ValidationCodeContainer(
            uiState = ValidationCodeUIState(),
            onEvent = {},
            paddingValues = PaddingValues(0.dp)
        )
    }
}
