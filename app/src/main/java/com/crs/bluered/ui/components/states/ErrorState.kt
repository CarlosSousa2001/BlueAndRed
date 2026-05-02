package com.crs.bluered.ui.components.states

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.crs.bluered.R
import com.crs.bluered.ui.theme.BlueRedTheme

@Composable
fun ErrorState(
    modifier: Modifier = Modifier,
    message: String?,
    onRetry: (() -> Unit)? = null
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(12.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
//        Image(
//            modifier = Modifier.size(250.dp),
//            painter = painterResource(R.drawable.ic_error_state),
//            contentDescription = null,
//        )
//        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(R.string.am_error_occurred_text),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(12.dp))

        message?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        onRetry?.let {
            Button(
                onClick = it,
                shape = MaterialTheme.shapes.small,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = stringResource(R.string.try_again_text),
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorStatePreview() {
    BlueRedTheme {
        ErrorState(
            message = "Por favor, tente novamente",
            onRetry = null
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorStatePreviewOnRetry() {
    BlueRedTheme {
        ErrorState(
            message = "Por favor, tente novamente",
            onRetry = {}
        )
    }
}