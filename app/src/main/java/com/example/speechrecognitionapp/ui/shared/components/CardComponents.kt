package com.example.speechrecognitionapp.ui.shared.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.speechrecognitionapp.R
import com.example.speechrecognitionapp.ui.screens.home.HomeScreen
import com.example.speechrecognitionapp.ui.theme.SpeechRecognitionAppTheme

@Composable
fun CardComponent(
    header: String,
    topEndText: String?,
    content: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(modifier = modifier) {
        Column(
            modifier = Modifier.padding(
                horizontal = dimensionResource(R.dimen.card_padding_horizontal),
                vertical = dimensionResource(R.dimen.card_padding_vertical)
            ),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    header
                )
                Spacer(modifier.weight(1f))
                NavigationRow({onClick()}, topEndText ?: "")
            }
            Text(content, style = MaterialTheme.typography.headlineMedium)

        }
    }
}

@Composable
fun NavigationRow(
    onClick: () -> Unit,
    label: String
) {
    Row(
        modifier = Modifier.clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            label,
            style = MaterialTheme.typography.labelSmall
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.size(10.dp)
        )
    }
}


@Preview(name = "HomeScreen", showSystemUi = true, device = "id:pixel_7")
@Composable
private fun PreviewHomeScreen() {
    SpeechRecognitionAppTheme {
        //HomeScreen()
    }
}