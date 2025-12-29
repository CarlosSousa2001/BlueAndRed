package com.crs.bluered.features.deck.list.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.crs.bluered.R
import com.crs.bluered.features.deck.list.domain.model.DeckListItem
import com.crs.bluered.ui.theme.PureWhite
import com.crs.bluered.ui.theme.SuccessLight
import com.crs.bluered.ui.theme.WarningLight
import com.crs.bluered.ui.theme.BlueRedThemeSizing.sizing
@Composable
fun DeckListItemCard(
    item: DeckListItem,
    modifier: Modifier = Modifier
) {

    val cs = MaterialTheme.colorScheme

    // ✅ paleta "base" (igual ideia do button: cores seguras e bonitas)
    val palette = remember(cs) {
        listOf(
            cs.primaryContainer,
            cs.secondaryContainer,
            cs.tertiaryContainer,
            SuccessLight,
            WarningLight
        )
    }

    val containerColor = remember(item.id, palette) {
        val index = kotlin.math.abs(item.id.hashCode()) % palette.size
        palette[index]
    }

    // ✅ contentColor simples: se for Success/Warning usa branco; senão usa on*Container
    val contentColor = remember(containerColor) {
        when (containerColor) {
            SuccessLight, WarningLight -> PureWhite
            else -> cs.onSurface // simples e seguro; se quiser mais “perfeito”, uso on*Container
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(192.dp) // 🔹 controla a “verticalidade” do card
            .background(
                color = containerColor,
                shape = RoundedCornerShape(sizing.md)
            )
            .border(
                width = 1.dp,
                color = cs.outline,
                shape = RoundedCornerShape(sizing.md)
            )
            .padding(sizing.md)
    ) {

        // 🔹 Título no topo
        Text(
            text = item.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = contentColor,
            maxLines = 2
        )

        // 🔹 Espaço grande entre título e contadores
        Spacer(modifier = Modifier.weight(1f))

        // 🔹 Contadores no rodapé do card
        Row(
            horizontalArrangement = Arrangement.spacedBy(sizing.m2d),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DeckCounter(
                iconRes = R.drawable.ic_deck_icon_24,
                value = item.cardsCount,
                tint = contentColor
            )

            DeckCounter(
                iconRes = R.drawable.ic_card_icon_24,
                value = item.optionsCount,
                tint = contentColor
            )
        }
    }
}
