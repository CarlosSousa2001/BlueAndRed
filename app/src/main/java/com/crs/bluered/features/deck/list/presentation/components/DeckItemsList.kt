package com.crs.bluered.features.deck.list.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.crs.bluered.features.deck.list.domain.model.DeckListItem
import com.crs.bluered.ui.theme.BlueRedThemeSizing.sizing

@Composable
fun DeckItemsList(
    items: List<DeckListItem>,
    gridState: LazyGridState,
    isLoadingMore: Boolean,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        state = gridState,
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(sizing.m2d),
        horizontalArrangement = Arrangement.spacedBy(sizing.m2d)
    ) {

        items(
            items = items,
            key = { it.id }
        ) { item ->
            DeckListItemCard(
                item = item
            )
        }

        if (isLoadingMore) {
            item(key = "loading_more") {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(sizing.md),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}
