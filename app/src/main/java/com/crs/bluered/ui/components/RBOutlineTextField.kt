package com.crs.bluered.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.crs.bluered.R
import com.crs.bluered.ui.theme.BlueRedTheme
import com.crs.bluered.ui.theme.BlueRedThemeSizing.sizing

@Composable
fun RBOutlineTextField(
    label: String,
    value: String,
    valueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorMessage: String? = null,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    maxLength: Int? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,

    leadingIcon: Painter? = null,
    trailingIcon: Painter? = null,
    onTrailingIconClick: (() -> Unit)? = null,

    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    Column(modifier = modifier) {

        OutlinedTextField(
            value = value,
            onValueChange = { newValue ->
                if (maxLength == null || newValue.length <= maxLength) {
                    valueChange(newValue)
                }
            },
            label = {
                Text(
                    text = label,
                    fontWeight = FontWeight.SemiBold
                )
            },

            leadingIcon = leadingIcon?.let { painter ->
                { Icon(painter = painter, contentDescription = null) }
            },

            trailingIcon = trailingIcon?.let { painter ->
                {
                    Icon(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            onTrailingIconClick?.invoke()
                        }
                    )
                }
            },

            isError = isError,
            enabled = enabled,
            singleLine = singleLine,
            visualTransformation = visualTransformation,
            shape = RoundedCornerShape(sizing.xs),
            modifier = Modifier.fillMaxWidth(),

            // 👇 Aqui entra teclado + ime
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = keyboardActions
        )

        if (isError && !errorMessage.isNullOrEmpty()) {
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = sizing.xs, start = sizing.sm)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RBOutlineTextFieldPreview() {
    BlueRedTheme {
        Column(
            modifier = Modifier.padding(sizing.sm),
            verticalArrangement = Arrangement.spacedBy(sizing.sm)
        ) {
            RBOutlineTextField(
                label = "Email",
                value = "",
                valueChange = {},
                leadingIcon = painterResource(id = R.drawable.ic_person_24),
                trailingIcon = painterResource(id = R.drawable.ic_send_24),
                isError = true,
                errorMessage = "Campo obrigatório",
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )

            RBOutlineTextField(
                label = "Senha",
                value = "",
                valueChange = {},
                leadingIcon = painterResource(id = R.drawable.ic_encrypted_24),
                trailingIcon = painterResource(id = R.drawable.ic_visibility_24),
                isError = false,
                errorMessage = "",
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        }
    }
}