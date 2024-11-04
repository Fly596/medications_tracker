package com.galeria.medicationstracker.ui.screens.autentification.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.ui.components.FlyButton
import com.galeria.medicationstracker.ui.components.FlyTextButton
import com.galeria.medicationstracker.ui.components.MyTextField
import com.galeria.medicationstracker.ui.screens.autentification.login.RememberMeSwitch
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

@Composable
fun SignupScreen(
  navigateHome: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: SignupScreenViewModel = viewModel(),
) {
  val state = viewModel.signupScreenState

  val email = state.email
  val password = state.password

  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text(
      stringResource(R.string.sign_up_screen_title),
      style = MedTrackerTheme.typography.largeTitle,
    )

    Spacer(modifier = Modifier.height(20.dp))

    MyTextField(
      value = email,
      onValueChange = { viewModel.updateEmail(it) },
      label = "Email",
      modifier = Modifier.fillMaxWidth(),
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
    )

    Spacer(modifier = Modifier.height(2.dp))
    var passwordVisibility by remember { mutableStateOf(false) }

    MyTextField(
      value = password,
      onValueChange = { viewModel.updatePassword(it) },
      label = "Password",
      modifier = Modifier.fillMaxWidth(),
      visualTransformation =
        if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    )
    // Show password switch.
    RememberMeSwitch(checked = passwordVisibility, onCheckedChange = { passwordVisibility = it })

    Spacer(modifier = Modifier.height(16.dp))

    val context = LocalContext.current

    Row(verticalAlignment = Alignment.CenterVertically) {
      FlyTextButton(onClick = navigateHome) { Text(text = "Cancel") }

      Spacer(modifier = Modifier.weight(1f))

      FlyButton(onClick = { viewModel.onRegisterClick(context, onSignupSuccess = navigateHome) }) {
        Text(text = "Create Account")
      }
    }
  }
}
