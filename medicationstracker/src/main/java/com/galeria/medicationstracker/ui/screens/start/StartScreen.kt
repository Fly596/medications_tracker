package com.galeria.medicationstracker.ui.screens.start

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun StartScreen(
    // userEmail: String,
    onNavigationSomewhere: () -> Unit, // TODO сделать навигацию.
    modifier: Modifier = Modifier
) {
    /*     val user = Firebase.auth.currentUser
        user?.let {
            val name = it.displayName
        } */

    Column(modifier) {
        Text(text = "userEmail")

        /*         Button(
                    onClick = onNavigationBack
                ) {

                } */
    }
}

@Preview(name = "StartScreen")
@Composable
private fun PreviewStartScreen() {
    // StartScreen("empty")
}