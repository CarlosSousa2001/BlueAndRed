package com.crs.bluered.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.crs.bluered.ui.theme.BlueRedTheme

@Composable
fun RBOtpCodeInput(
    modifier: Modifier = Modifier,
    length: Int = 6,
    value: String,
    onValueChange: (String) -> Unit,
    onComplete: (String) -> Unit = {}
) {
    val focusRequester = remember { FocusRequester() }

    // Campo "invisível" que realmente recebe o input
    BasicTextField(
        value = value,
        onValueChange = { newValue ->
            if (newValue.length <= length && newValue.all { it.isDigit() }) {
                onValueChange(newValue)

                if (newValue.length == length) {
                    onComplete(newValue)
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword
        ),
        modifier = Modifier
            .focusRequester(focusRequester)
            .size(0.dp) // invisível
    )

    // UI das caixas
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        repeat(length) { index ->
            val char = value.getOrNull(index)?.toString() ?: ""
            val isFocused = value.length == index

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .border(
                        width = 2.dp,
                        color = if (isFocused)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.outlineVariant,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable {
                        focusRequester.requestFocus()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = char,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }

    // autofocus automático
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Preview(
    name = "OTP Light",
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun OtpCodeInputPreviewLight() {
    BlueRedTheme {
        var otp by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RBOtpCodeInput(
                value = otp,
                onValueChange = { otp = it },
                onComplete = { code ->
                    println("Código completo: $code")
                }
            )
        }
    }
}

@Preview(
    name = "OTP Dark",
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun OtpCodeInputPreviewDark() {
    BlueRedTheme {
        var otp by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RBOtpCodeInput(
                value = otp,
                onValueChange = { otp = it },
                onComplete = { code ->
                    println("Código completo: $code")
                }
            )
        }
    }
}
