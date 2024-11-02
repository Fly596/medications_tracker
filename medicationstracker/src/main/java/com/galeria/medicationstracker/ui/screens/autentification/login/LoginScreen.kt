package com.galeria.medicationstracker.ui.screens.autentification.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.ui.shared.components.HIGButton
import com.galeria.medicationstracker.ui.shared.components.HIGButtonStyle
import com.galeria.medicationstracker.ui.shared.components.MySwitch
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

@Composable
fun LoginScreen(
  onLoginClick: () -> Unit,
  onSignupClick: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: LoginScreenViewModel = viewModel(),
) {
  val state = viewModel.loginScreenState

  Column(
    modifier,
    verticalArrangement = Arrangement.Center,
  ) {
    Text(
      stringResource(R.string.sign_in_screen_title),
      style = MedTrackerTheme.typography.largeTitle
    )

    Spacer(modifier = Modifier.height(20.dp))

    // Card() {
    LazyColumn(
      modifier
    ) {
      item {
        MyTextField(
          value = state.email,
          onValueChange = { viewModel.updateEmail(it) },
          label = "Email",
          modifier,
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
      }
      item {
        HorizontalDivider(
          modifier = Modifier
            .fillMaxWidth()
            .padding(
              start = 24.dp,
              end = 12.dp
            ),
          color = MedTrackerTheme.colors.separator
        )
      }

      item {
        MyTextField(
          value = state.password,
          onValueChange = { viewModel.updatePassword(it) },
          label = "Password",
          modifier,
          visualTransformation = if (state.showPassword) VisualTransformation.None else PasswordVisualTransformation(),
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
      }
    }

    // Show password switch.
    RememberMeSwitch(
      checked = state.showPassword,
      onCheckedChange = {
        viewModel.isShowPasswordChecked(state.showPassword)
      }
    )

    Spacer(modifier = Modifier.height(16.dp))

    // region Buttons
    val context = LocalContext.current
    Row(verticalAlignment = Alignment.CenterVertically) {
      HIGButton(
        text = "Sign In",
        onClick = {
          // ! TEMP: для простого входа.
          if (state.email == "sex") {
            viewModel.onSignInClick(
              "ggsell@gmail.com", "password", context, onLoginClick
            )
          } else {
            viewModel.onSignInClick(
              state.email,
              state.password,
              context,
              onLoginClick
            )
          }
        },
        enabled = true,
        style = HIGButtonStyle.Filled
      )

      Spacer(modifier = Modifier.weight(1f))

      HIGButton(
        text = "Create Account",
        onClick = { onSignupClick() },
        enabled = true,
        style = HIGButtonStyle.Bezeled
      )
    }

    // Spacer(modifier = Modifier.weight(1f))

    HIGButton(
      text = "Forgot Password?",
      onClick = { viewModel.resetPassword(state.email, context) },
      enabled = true,
      style = HIGButtonStyle.Borderless
    )
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
    modifier = modifier,
    colors = TextFieldDefaults.colors(
      unfocusedContainerColor = MedTrackerTheme.colors.secondaryBackground,
      unfocusedIndicatorColor = Color.Transparent,
      disabledIndicatorColor = Color.Transparent,
      focusedIndicatorColor = Color.Transparent,
      errorIndicatorColor = Color.Transparent,
    )
  )
}


@Composable
fun RememberMeSwitch(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
  Row(verticalAlignment = Alignment.CenterVertically) {
    Text("Show password")
    Spacer(modifier = Modifier.width(12.dp))

    MySwitch(checked = checked, onCheckedChange = onCheckedChange)
  }
}


@Preview(name = "LoginScreen")
@Composable
private fun PreviewLoginScreen() {
  // LoginScreen()
}
