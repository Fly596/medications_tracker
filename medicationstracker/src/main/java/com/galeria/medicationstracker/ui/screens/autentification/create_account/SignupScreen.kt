package com.galeria.medicationstracker.ui.screens.autentification.create_account


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.ui.screens.autentification.login.MyTextField
import com.galeria.medicationstracker.ui.shared.components.HIGButton
import com.galeria.medicationstracker.ui.shared.components.HIGButtonStyle

@Composable
fun SignupScreen(
    onCreateAccountClick: () -> Unit,
    viewModel: SignupScreenViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val email = viewModel.username.collectAsState()
    val password = viewModel.password.collectAsState()
    val userName = viewModel.userName.collectAsState()

    /*     val user = Firebase.auth.currentUser
        user?.let {
            val name = it.displayName
        } */

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

        MyTextField(
            value = email.value,
            onValueChange = { viewModel.updateEmail(it) },
            label = "Email",
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(16.dp))

        MyTextField(
            value = password.value,
            onValueChange = { viewModel.updatePassword(it) },
            label = "Password",
            modifier = Modifier
                .fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(16.dp))

        /*         MyTextField(
                    value = userName.value,
                    onValueChange = { viewModel.updateUserName(it) },
                    label = "User name",
                    modifier = Modifier
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                ) */

        Spacer(modifier = Modifier.height(16.dp))

        HIGButton(
            text = "Create Account",
            onClick = {

                // Создание аккаунта.
                viewModel.onRegisterClick(
                    email.value,
                    password.value,
                    // userName.value,
                    context,
                    onSignupClick = onCreateAccountClick
                )
                // Вход в только что созданный аккаунт
                /*                 viewModelLogin.onSignInClick(
                                    email.value,
                                    password.value,
                                    context,
                                    onLoginClick
                                )
                                // Добавление информации о текущем пользователе в базу данных.
                                viewModel.addUserInfo(
                                    Firebase.auth.currentUser?.uid.toString()
                                ) */
            },
            enabled = true,
            style = HIGButtonStyle.Bezeled
        )
    }
}
