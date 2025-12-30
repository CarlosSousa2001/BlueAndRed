package com.crs.bluered.features.deck.create.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.toSize
import com.crs.bluered.shared.domain.enums.DeckVisibility

@Composable
fun DeckVisibilityDropdownMenu(
    modifier: Modifier = Modifier,
    value: String, // "PUBLIC" | "PRIVATE"
    onValueChange: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val focusManager = LocalFocusManager.current

    val selectedLabel = remember(value) {
        DeckVisibility.fromApiValue(value)?.label ?: "Selecione"
    }

    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedLabel,
            onValueChange = {},
            readOnly = true,
            label = {
                Text(
                    text = "Visibilidade",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.outline,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline
            ),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { expanded = it.isFocused }
                .onGloballyPositioned { coords ->
                    textFieldSize = coords.size.toSize()
                }
        )

        DropdownMenu(
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                .background(MaterialTheme.colorScheme.background),
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                focusManager.clearFocus()
            }
        ) {
            DeckVisibility.entries.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onValueChange(option.apiValue) // ✅ "PUBLIC" | "PRIVATE"
                        expanded = false
                        focusManager.clearFocus()
                    },
                    text = {
                        Text(
                            text = option.label, // ✅ "Público" | "Privado"
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    colors = MenuDefaults.itemColors()
                )
            }
        }
    }
}
