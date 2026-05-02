package com.crs.bluered.features.deck.create.presentation.components

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
import com.crs.bluered.shared.domain.enums.DeckVisibility
import com.crs.bluered.ui.components.BRButton
import com.crs.bluered.ui.components.ButtonStyle
import com.crs.bluered.ui.components.RBOutlineTextField
import com.crs.bluered.ui.theme.BlueRedTheme
import com.crs.bluered.ui.theme.BlueRedThemeSizing.sizing

@Composable
fun CreateDeckForm(
    modifier: Modifier = Modifier,
    title: InputFieldState,
    visibility: String,
    maxMembers: InputFieldState,
    isLoading: Boolean,
    generalErrorMessage: String?,
    onTitleChange: (String) -> Unit,
    onVisibilityChange: (String) -> Unit,
    onMaxMembersChange: (String) -> Unit,
    onSubmit: () -> Unit,
) {


    Column(
        modifier = modifier.padding(sizing.md),
        verticalArrangement = Arrangement.spacedBy(sizing.sm)
    ) {
        RBOutlineTextField(
            label = "Title",
            value = title.value,
            valueChange = onTitleChange,
            isError = !title.errorMessage.isNullOrEmpty(),
            errorMessage = title.errorMessage,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
        )

        DeckVisibilityDropdownMenu(
            value = visibility,
            onValueChange = onVisibilityChange
        )

        RBOutlineTextField(
            label = "Máximo de membros",
            value = maxMembers.value,
            valueChange = onMaxMembersChange,
            isError = !maxMembers.errorMessage.isNullOrEmpty(),
            errorMessage = maxMembers.errorMessage,
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next,
            maxLength = 2
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
private fun CreateDeckFormPreview() {
    BlueRedTheme {
        CreateDeckForm(
            title = InputFieldState(
                value = "Meu Deck Incrível",
                errorMessage = null
            ),
            visibility = DeckVisibility.PUBLIC.apiValue,
            maxMembers = InputFieldState(
                value = "10",
                errorMessage = null
            ),
            isLoading = false,
            generalErrorMessage = null,
            onTitleChange = {},
            onVisibilityChange = {},
            onMaxMembersChange = {},
            onSubmit = {}
        )
    }
}