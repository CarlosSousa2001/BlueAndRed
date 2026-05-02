package com.crs.bluered.features.card.create.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.crs.bluered.core.domain.model.InputFieldState
import com.crs.bluered.ui.components.BRButton
import com.crs.bluered.ui.components.ButtonStyle
import com.crs.bluered.ui.components.RBOutlineTextField
import com.crs.bluered.ui.theme.BlueRedTheme
import com.crs.bluered.ui.theme.BlueRedThemeSizing.sizing

@Composable
fun CreateCardForm(
    modifier: Modifier = Modifier,
    label: InputFieldState,
    title: InputFieldState,
    isLoading: Boolean,
    generalErrorMessage: String?,
    onTitleChange: (String) -> Unit,
    onLabelChange: (String) -> Unit,
    onSubmit: () -> Unit,
) {
    Column(
        modifier = modifier.padding(sizing.md),
        verticalArrangement = Arrangement.spacedBy(sizing.sm)
    ) {

        RBOutlineTextField(
            label = "Label",
            value = label.value,
            valueChange = onLabelChange,
            isError = !label.errorMessage.isNullOrEmpty(),
            errorMessage = label.errorMessage,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
        )


        RBOutlineTextField(
            label = "Title",
            value = title.value,
            valueChange = onTitleChange,
            isError = !title.errorMessage.isNullOrEmpty(),
            errorMessage = title.errorMessage,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
        )

        if(!generalErrorMessage.isNullOrEmpty()) {
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
            text = "Salvar",
            onClick = onSubmit,
            enabled = !isLoading,
            loading = isLoading,
            style = ButtonStyle.Primary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = sizing.sm)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CreateCardFormPreview() {
    BlueRedTheme {
        CreateCardForm(
            label = InputFieldState(
                value = "Meu Deck Incrível",
                errorMessage = null
            ),
            title = InputFieldState(
                value = "Meu Deck Incrível",
                errorMessage = null
            ),
            isLoading = false,
            generalErrorMessage = null,
            onTitleChange = {},
            onLabelChange = {},
            onSubmit = {}
        )
    }
}