package com.crs.bluered.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.crs.bluered.ui.theme.BlueRedTheme
import com.crs.bluered.ui.theme.BlueRedThemeSizing.sizing
import com.crs.bluered.ui.theme.PureWhite
import com.crs.bluered.ui.theme.SuccessLight
import com.crs.bluered.ui.theme.WarningLight

enum class ButtonStyle {
    Primary,
    Secondary,
    Success,
    Alert,
    Danger,
}

@Composable
fun BRButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: ButtonStyle = ButtonStyle.Primary,
    enabled: Boolean = true,
    loading: Boolean = false,
    leadingIcon: (@Composable (() -> Unit))? = null,
    trailingIcon: (@Composable (() -> Unit))? = null,
) {
    val cs = MaterialTheme.colorScheme

    val colors = when (style) {
        ButtonStyle.Primary -> ButtonDefaults.buttonColors(
            containerColor = cs.primary,
            contentColor = cs.onPrimary,
            disabledContainerColor = cs.primary.copy(alpha = 0.75f),
            disabledContentColor = cs.onSurface.copy(alpha = 0.38f)
        )

        ButtonStyle.Secondary -> ButtonDefaults.buttonColors(
            containerColor = cs.secondaryContainer,
            contentColor = cs.onSecondaryContainer,
            disabledContainerColor = cs.secondaryContainer.copy(alpha = 0.75f),
            disabledContentColor = cs.onSurface.copy(alpha = 0.38f)
        )

        ButtonStyle.Success -> ButtonDefaults.buttonColors(
            containerColor = SuccessLight,
            contentColor = PureWhite,
            disabledContainerColor = SuccessLight.copy(alpha = 0.75f),
            disabledContentColor = cs.onSurface.copy(alpha = 0.38f)
        )

        ButtonStyle.Alert -> ButtonDefaults.buttonColors(
            containerColor = WarningLight,
            contentColor = PureWhite,
            disabledContainerColor = WarningLight.copy(alpha = 0.75f),
            disabledContentColor = cs.onSurface.copy(alpha = 0.38f)
        )

        ButtonStyle.Danger -> ButtonDefaults.buttonColors(
            containerColor = cs.error,
            contentColor = cs.onError,
            disabledContainerColor = cs.error.copy(alpha = 0.75f),
            disabledContentColor = WarningLight.copy(alpha = 0.38f)
        )
    }

    val spinnerColor = when (style) {
        ButtonStyle.Primary -> cs.onPrimary
        ButtonStyle.Secondary -> cs.onSecondaryContainer
        ButtonStyle.Success -> PureWhite
        ButtonStyle.Alert -> PureWhite
        ButtonStyle.Danger -> cs.onError
    }

    Button(
        onClick = onClick,
        enabled = enabled && !loading,
        colors = colors,
        shape = RoundedCornerShape(sizing.xs),
        contentPadding = ButtonDefaults.ContentPadding,
        modifier = modifier.height(sizing.x2l)
    ) {

        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(sizing.lg),
                strokeWidth = sizing.xs / 2,
                color = spinnerColor
            )
            return@Button
        }


        if (leadingIcon != null) {
            leadingIcon()
            Spacer(Modifier.width(sizing.sm))
        }

        Text(text = text, style = MaterialTheme.typography.labelLarge)

        if (trailingIcon != null) {
            Spacer(Modifier.width(sizing.sm))
            trailingIcon()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BRButtonPreview() {
    BlueRedTheme {
        Column(
            modifier = Modifier.padding(sizing.sm),
            verticalArrangement = Arrangement.spacedBy(sizing.sm)
        ) {
            BRButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Button",
                onClick = {},
                style = ButtonStyle.Primary,
                enabled = true,
            )

            BRButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Button",
                onClick = {},
                style = ButtonStyle.Secondary,
                enabled = true,
            )

            BRButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Button",
                onClick = {},
                style = ButtonStyle.Success,
                enabled = true,
            )

            BRButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Button",
                onClick = {},
                style = ButtonStyle.Alert,
                enabled = true,
            )

            BRButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Button",
                onClick = {},
                style = ButtonStyle.Danger,
                enabled = true,
            )
        }
    }
}