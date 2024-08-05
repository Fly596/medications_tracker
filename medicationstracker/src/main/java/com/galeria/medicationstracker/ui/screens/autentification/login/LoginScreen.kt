package com.galeria.medicationstracker.ui.screens.autentification.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.ui.shared.components.HIGButton
import com.galeria.medicationstracker.ui.shared.components.HIGButtonStyle
import com.galeria.medicationstracker.ui.theme.MedicationsTrackerAppTheme

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onSignupClick: () -> Unit,
    viewModel: LoginScreenViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val email = viewModel.username.collectAsState()
    val password = viewModel.password.collectAsState()
    var checked by remember { mutableStateOf(true) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                stringResource(R.string.sign_in_screen_title),
                style = MedicationsTrackerAppTheme.extendedTypography.body)

            Spacer(modifier = Modifier.height(20.dp))

            MyTextField(
                value = email.value,
                onValueChange = { viewModel.updateEmail(it) },
                label = "Email",
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email))

            Spacer(modifier = Modifier.height(16.dp))

            MyTextField(
                value = password.value,
                onValueChange = { viewModel.updatePassword(it) },
                label = "Password",
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password))

            // region Buttons
            Spacer(modifier = Modifier.height(16.dp))

            RememberMeSwitch(checked = checked, onCheckedChange = { checked = it })
            // Switch(checked = checked, onCheckedChange = { checked = it })

            HIGButton(
                text = "Sign In",
                onClick = {
                    // ! TEMP: для простого входа
                    if (email.value == "sex") {
                        viewModel.onSignInClick(
                            "ggsell@gmail.com", "password", context, onLoginClick)
                    } else {
                        viewModel.onSignInClick(email.value, password.value, context, onLoginClick)
                    }
                },
                enabled = true,
                style = HIGButtonStyle.Filled)

            Spacer(modifier = Modifier.height(16.dp))

            HIGButton(
                text = "Create Account",
                onClick = { onSignupClick.invoke() },
                enabled = true,
                style = HIGButtonStyle.Bezeled)

            HIGButton(
                text = "Forgot Password?",
                onClick = { viewModel.resetPassword(email.value, context) },
                enabled = true,
                style = HIGButtonStyle.Borderless)
            // endregion
        }
}

@Composable
fun MyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        modifier = modifier)
}

// TODO: Textfields; Buttons

@Composable
fun RememberMeSwitch(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Switch(checked = checked, onCheckedChange = onCheckedChange)
}

@Preview(name = "LoginScreen")
@Composable
private fun PreviewLoginScreen() {
    // LoginScreen()
}
