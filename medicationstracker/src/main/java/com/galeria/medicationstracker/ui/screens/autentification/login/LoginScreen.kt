package com.galeria.medicationstracker.ui.screens.autentification.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.ui.components.FlyButton
import com.galeria.medicationstracker.ui.components.FlyTextButton
import com.galeria.medicationstracker.ui.components.FlyTonalButton
import com.galeria.medicationstracker.ui.components.MySwitch
import com.galeria.medicationstracker.ui.components.MyTextField
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

@Composable
fun LoginScreen(
  modifier: Modifier = Modifier,
  onLoginClick: () -> Unit,
  onSignupClick: () -> Unit,
  viewModel: LoginScreenViewModel = viewModel(),
) {
  val state = viewModel.loginScreenState

  Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
    Text(
      stringResource(R.string.sign_in_screen_title),
      style = MedTrackerTheme.typography.largeTitleEmphasized,
    )

    Spacer(modifier = Modifier.weight(1f))

    LazyColumn(verticalArrangement = Arrangement.spacedBy(2.dp), modifier = Modifier) {
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
          placeholder = "At least 6 characters",
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

    // region Buttons
    val context = LocalContext.current
    Row(verticalAlignment = Alignment.CenterVertically) {
      val scope = rememberCoroutineScope()

      FlyButton(
        onClick = {
          viewModel.onSignInClick(state.email, state.password, context, onLoginClick)

          /* scope.launch {
            viewModel.onSignInClick(state.email, state.password, context, onLoginClick)
          } */
        },
        enabled = true,
      ) {
        Text(text = "Sign In")
      }

      Spacer(modifier = Modifier.weight(1f))

      FlyTonalButton(onClick = { onSignupClick() }, enabled = true) {
        Text(text = "Create Account")
      }
    }

    // Spacer(modifier = Modifier.weight(1f))
    Spacer(modifier = Modifier.weight(1f))

    FlyTextButton(onClick = { viewModel.resetPassword(state.email, context) }, enabled = true) {
      Text(text = "Forgot password?")
    }

    // endregion
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
