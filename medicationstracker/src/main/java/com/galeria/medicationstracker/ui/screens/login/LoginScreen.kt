package com.galeria.medicationstracker.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
        Text(stringResource(R.), style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* TODO: Log in action */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Log in")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { /* TODO: Create account action */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Create account")
        }

        TextButton(
            onClick = { /* TODO: Forgot password action */ }
        ) {
            Text("Forgot password?")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { /* TODO: Sign-in with Google action */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sign-in with Google account")
        }
    }
}


@Composable
fun InputFields(modifier: Modifier = Modifier) {

}


@Preview(name = "LoginScreen")
@Composable
private fun PreviewLoginScreen() {
    LoginScreen()
}