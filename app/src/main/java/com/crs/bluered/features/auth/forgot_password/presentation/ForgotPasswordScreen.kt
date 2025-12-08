package com.crs.bluered.features.auth.forgot_password.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.crs.bluered.R
import com.crs.bluered.features.auth.forgot_password.presentation.components.ForgotPasswordForm
import com.crs.bluered.ui.theme.BlueRedTheme

@Composable
fun ForgotPasswordScreen(
    uiState: ForgotPasswordUIState,
    onEvent: (ForgotPasswordEvent) -> Unit,
    onNavigateToVerifyCodeScreen: () -> Unit,
) {
    Scaffold(
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.TopCenter
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.bg_forgot_password_img_foreground),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.43f)
                    )


                    ForgotPasswordForm(
                        email = uiState.email,
                        isLoading = uiState.isLoading,
                        generalErrorMessage = uiState.generalErrorMessage,
                        onEmailChange = { onEvent(ForgotPasswordEvent.OnEmailChanged(it)) },
                        onSubmit = { onEvent(ForgotPasswordEvent.Submit) }
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun ForgotPasswordScreenPreview() {
    BlueRedTheme {
        ForgotPasswordScreen(
            uiState = ForgotPasswordUIState(),
            onEvent = {},
            onNavigateToVerifyCodeScreen = {}
        )
    }
}