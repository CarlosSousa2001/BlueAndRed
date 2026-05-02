package com.crs.bluered.features.deck.list.presentation.components

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.crs.bluered.R
import com.crs.bluered.features.deck.list.domain.model.DeckListItem
import com.crs.bluered.ui.theme.BlueRedThemeSizing.sizing
import com.crs.bluered.ui.theme.DeckCardMetallicPalette
import com.crs.bluered.ui.theme.PureWhite

@Composable
fun DeckListItemCard(
    item: DeckListItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val gradient = remember(item.id) {
        val index = kotlin.math.abs(item.id.hashCode()) % DeckCardMetallicPalette.size
        DeckCardMetallicPalette[index]
    }

    val gradientBrush = remember(gradient) {
        Brush.linearGradient(
            colors = listOf(gradient.from, gradient.to),
            start = Offset(0f, 0f),
            end = Offset(800f, 600f)
        )
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(192.dp)
            .background(
                brush = gradientBrush,
                shape = RoundedCornerShape(sizing.md)
            )
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.18f),
                shape = RoundedCornerShape(sizing.md)
            )
            .clickable(onClick = onClick)
            .padding(sizing.md)
    ) {

        Text(
            text = item.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.ExtraBold,
            color = PureWhite,
            maxLines = 2
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            horizontalArrangement = Arrangement.spacedBy(sizing.m2d),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DeckCounter(
                iconRes = R.drawable.ic_deck_icon_24,
                value = item.cardsCount,
                tint = PureWhite
            )

            DeckCounter(
                iconRes = R.drawable.ic_card_icon_24,
                value = item.optionsCount,
                tint = PureWhite
            )
        }
    }
}
