package com.galeria.medicationstracker.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.ui.shared.components.HIGButton
import com.galeria.medicationstracker.ui.shared.components.HIGButtonStyle

@Composable
fun LoginScreen(
    viewModel: LoginScreenViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val email = viewModel.username.collectAsState()
    val password = viewModel.password.collectAsState()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(R.string.sign_in_screen_title),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))


        /*         TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                ) */

        Spacer(modifier = Modifier.height(16.dp))

        /*         TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                ) */

        Spacer(modifier = Modifier.height(16.dp))

        HIGButton(
            text = "Sign In",
            onClick = {  /* TODO: Log in action */ },
            enabled = true,
            style = HIGButtonStyle.Filled
        )

        Spacer(modifier = Modifier.height(16.dp))


        HIGButton(
            text = "Create Account",
            onClick = {  /* TODO: Create account action */ },
            enabled = true,
            style = HIGButtonStyle.Borderless
        )
        HIGButton(
            text = "Forgot Password?",
            onClick = {  /* TODO: Forgot password action */ },
            enabled = true,
            style = HIGButtonStyle.Borderless
        )

        Spacer(modifier = Modifier.height(32.dp))


        HIGButton(
            text = "Sign-in with Google account",
            onClick = { /* TODO: Sign-in with Google action */ },
            enabled = true,
            style = HIGButtonStyle.Filled
        )

    }
}

// TODO: Textfields; Buttons

@Composable
fun InputFields(modifier: Modifier = Modifier) {

}


@Preview(name = "LoginScreen")
@Composable
private fun PreviewLoginScreen() {
    // LoginScreen()
}