package com.galeria.medicationstracker.ui.screens.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DashboardScreen(
    // userEmail: String,
    onProfileNavigate: () -> Unit, // TODO сделать навигацию.
    onCalendarNavigate: () -> Unit, // TODO сделать навигацию.
    modifier: Modifier = Modifier
) {
    /*     val user = Firebase.auth.currentUser
    user?.let {
        val name = it.displayName
    } */

    Column(modifier) { Button(onClick = onProfileNavigate) { Text(text = "Profile") } }
}

@Preview(name = "StartScreen")
@Composable
private fun PreviewStartScreen() {
    // StartScreen("empty")
}
