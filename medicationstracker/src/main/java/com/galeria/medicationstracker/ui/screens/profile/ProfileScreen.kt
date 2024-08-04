package com.galeria.medicationstracker.ui.screens.profile


import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProfileScreen(
    onNavigateToCalendar: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "ProfileScreen")
    }
}

@Preview(name = "ProfileScreen")
@Composable
private fun PreviewProfileScreen() {
    ProfileScreen()
}