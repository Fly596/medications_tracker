package com.galeria.medicationstracker.ui.screens.auth.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.text.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.*
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.data.*
import com.galeria.medicationstracker.ui.components.*
import com.galeria.medicationstracker.ui.theme.*

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginClick: (userType: UserType) -> Unit,
    onSignupClick: (String) -> Unit,
    onResetPasswordClick: (String) -> Unit,
    viewModel: LoginScreenViewModel = viewModel(),
) {
  val state = viewModel.loginScreenState

  Column(
    modifier = modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Top
  ) {
    Text(
      stringResource(R.string.sign_in_screen_title),
      style = MedTrackerTheme.typography.largeTitleEmphasized,
    )

    Spacer(modifier = Modifier.weight(1f))

    LazyColumn(
      verticalArrangement = Arrangement.spacedBy(2.dp),
      modifier = Modifier
    ) {
      item {
        MyTextField(
          value = state.email,
          onValueChange = { viewModel.updateEmail(it) },
          isError = state.emailError?.isNotEmpty() ?: false,
          errorMessage = state.emailError,
          label = "Email",
          placeholder = "",
          modifier = Modifier.fillMaxWidth(),
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        )
      }

      item {
        MyTextField(
          value = state.password,
          onValueChange = { viewModel.updatePassword(it) },
          isError = state.passwordError?.isNotEmpty() ?: false,
          errorMessage = state.passwordError,
          label = "Password",
          placeholder = "6 or more characters",
          // supportingText = "6 or more characters",
          modifier = Modifier.fillMaxWidth(),
          visualTransformation =
            if (state.showPassword) VisualTransformation.None else PasswordVisualTransformation(),
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        )
      }
    }

    // Show password switch.
    RememberMeSwitch(
      checked = state.showPassword,
      onCheckedChange = { viewModel.isShowPasswordChecked(state.showPassword) },
    )

    Spacer(modifier = Modifier.height(16.dp))

    Row(verticalAlignment = Alignment.CenterVertically) {
      val scope = rememberCoroutineScope()

      FlyButton(
        onClick = {
          // Запрос типа пользователя.
          viewModel.getUserType()

          viewModel.onSignInClick(state.email, state.password) { userType ->
            onLoginClick(userType)
          }

        },
        enabled = true,
      ) {
        Text(text = "Sign In")
      }

      Spacer(modifier = Modifier.weight(1f))

      FlyTonalButton(onClick = { onSignupClick(state.email) }, enabled = true) {
        Text(text = "Create Account")
      }
    }

    Spacer(modifier = Modifier.weight(1f))

    FlyTextButton(
      onClick = { onResetPasswordClick(state.email) },
      enabled = true
    ) {
      Text(text = "Forgot password?")
    }
  }
}

@Composable
fun RememberMeSwitch(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
  Row(verticalAlignment = Alignment.CenterVertically) {
    Text("Show password", style = MedTrackerTheme.typography.body)
    Spacer(modifier = Modifier.width(12.dp))

    MySwitch(checked = checked, onCheckedChange = onCheckedChange)
  }
}
