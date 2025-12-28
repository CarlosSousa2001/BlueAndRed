package com.crs.bluered.ui.components.states

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.crs.bluered.R
import com.crs.bluered.ui.theme.BlueRedTheme

@Composable
fun EmptyState(
    modifier: Modifier = Modifier,
    image: Painter,
    title: String,
    children: @Composable () -> Unit = {}
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(250.dp),
                painter = image,
                contentDescription = null,
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp,
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            children()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyStatePreview() {
    BlueRedTheme{
        EmptyState(
            image = painterResource(R.drawable.bg_empty_state),
            title = "Nenhum card encontrado",
            children = {
                Text(
                    text = "Por favor, tente novamente",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        )
    }
}