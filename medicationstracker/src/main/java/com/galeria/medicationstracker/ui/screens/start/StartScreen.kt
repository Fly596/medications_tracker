package com.galeria.medicationstracker.ui.screens.start

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun StartScreen(
    userEmail: String,
    onNavigationBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Button(
            onClick = onNavigationBack
        ) {
            Text(text = userEmail)

        }
    }
}

@Preview(name = "StartScreen")
@Composable
private fun PreviewStartScreen() {
    // StartScreen("empty")
}