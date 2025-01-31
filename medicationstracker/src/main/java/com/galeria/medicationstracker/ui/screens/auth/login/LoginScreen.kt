package com.galeria.medicationstracker.ui.screens.auth.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.*
import androidx.lifecycle.compose.*
import androidx.lifecycle.viewmodel.compose.*
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.data.*
import com.galeria.medicationstracker.ui.componentsOld.*
import com.galeria.medicationstracker.ui.theme.*

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLogin: () -> Unit = {},
    onRegistration: () -> Unit = {},
    onResetPassword: () -> Unit = {},
    onLoginClick: (userType: UserType) -> Unit = {},
    viewModel: LoginScreenViewModel = viewModel(),
) {
    val state = viewModel.loginScreenState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            stringResource(R.string.sign_in_screen_title),
            style = MedTrackerTheme.typography.display2Emphasized,
        )

        Spacer(modifier = Modifier.weight(1f))

        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier
        ) {
            MyTextField(
                value = state.value.email,
                onValueChange = { viewModel.updateEmail(it) },
                isPrimaryColor = true,
                isError = state.value.emailError?.isNotEmpty() ?: false,
                errorMessage = state.value.emailError,
                label = "Email",
                placeholder = "",
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            )

            MyTextField(
                value = state.value.password,
                onValueChange = { viewModel.updatePassword(it) },
                isPrimaryColor = true,
                isError = state.value.passwordError?.isNotEmpty() ?: false,
                errorMessage = state.value.passwordError,
                label = "Password",
                placeholder = "6 or more characters",
                // supportingText = "6 or more characters",
                modifier = Modifier.fillMaxWidth(),
                visualTransformation =
                    if (state.value.showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )
        }
        // Show password switch.
        RememberMeSwitch(
            checked = state.value.showPassword,
            onCheckedChange = { viewModel.isShowPasswordChecked(state.value.showPassword) },
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            val scope = rememberCoroutineScope()

            FlyButton(
                onClick = {
                    // Запрос типа пользователя.
                    viewModel.getUserType()

                    viewModel.onSignInClick(
                        state.value.email,
                        state.value.password
                    ) { userType ->
                        onLoginClick(userType)
                    }
                },
                enabled = true,
            ) {
                Text(text = "Sign In")
            }

            Spacer(modifier = Modifier.weight(1f))

            FlyTonalButton(
                onClick = {
                    onRegistration()
                },
                enabled = true
            ) {
                Text(text = "Create Account")
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        FlyTextButton(
            onClick = {
                onResetPassword()
            },
            enabled = true
        ) {
            Text(text = "Forgot password?")
        }
    }
}

@Composable
fun RememberMeSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            "Show password",
            style = MedTrackerTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.width(12.dp))

        MySwitch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}
