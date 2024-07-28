package com.example.speechrecognitionapp.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.speechrecognitionapp.R
import com.example.speechrecognitionapp.ui.shared.components.ButtonComponent
import com.example.speechrecognitionapp.ui.shared.components.SecondaryButtonComponent

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val userName = viewModel.username.collectAsState()
    val password = viewModel.password.collectAsState()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextFieldComponent(
            text = userName,
            label = "Username",
            onValueChange = { viewModel.updateEmail(it) },
            corners = arrayOf(
                dimensionResource(R.dimen.padding_medium),
                dimensionResource(R.dimen.padding_medium),
                0.dp,
                0.dp
            )
        )
        TextFieldComponent(
            text = password,
            label = "Password",
            onValueChange = { viewModel.updatePassword(it) },
            visualTransformation = PasswordVisualTransformation(),
            corners = arrayOf(
                0.dp,
                0.dp,
                dimensionResource(R.dimen.padding_medium),
                dimensionResource(R.dimen.padding_medium)
            )
        )

        ButtonComponent(
            text = R.string.sign_in_button,
            onClick = { viewModel.onSignInClick(userName.value, password.value, context) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        SecondaryButtonComponent(
            text = R.string.sign_up_button,
            onClick = { viewModel.onSignUpClick(userName.value, password.value, context) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
    }
}

@Composable
private fun TextFieldComponent(
    text: State<String>,
    label: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    corners: Array<Dp> = arrayOf(),
) {
    OutlinedTextField(
        value = text.value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(
            topStart = corners[0],
            topEnd = corners[1],
            bottomStart = corners[2],
            bottomEnd = corners[3]
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(name = "LoginScreen")
@Composable
private fun PreviewLoginScreen() {
    // LoginScreen()
}