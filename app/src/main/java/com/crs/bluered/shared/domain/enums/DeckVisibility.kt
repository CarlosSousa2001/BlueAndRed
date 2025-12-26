package com.crs.bluered.shared.domain.enums

enum class DeckVisibility(
    val apiValue: String,
    val label: String
) {
    PUBLIC(apiValue = "PUBLIC", label = "Público"),
    PRIVATE(apiValue = "PRIVATE", label = "Privado");

    companion object {
        fun fromApiValue(value: String): DeckVisibility? =
            entries.firstOrNull { it.apiValue.equals(value, ignoreCase = true) }

        fun fromLabel(label: String): DeckVisibility? =
            entries.firstOrNull { it.label.equals(label, ignoreCase = true) }
    }
}
