package com.galeria.medicationstracker.ui.screens.auth.signup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.*
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.data.*
import com.galeria.medicationstracker.ui.components.*
import com.galeria.medicationstracker.ui.screens.auth.login.*
import com.galeria.medicationstracker.ui.theme.*

@Composable
fun SignupScreen(
  passedEmail: String,
  navigateHome: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: SignupScreenViewModel = viewModel(),
) {
  LaunchedEffect(Unit) { viewModel.updateEmail(passedEmail) }
  
  val state = viewModel.signupScreenState
  
  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Top
  ) {
    Text(
      stringResource(R.string.sign_up_screen_title),
      style = MedTrackerTheme.typography.largeTitleEmphasized,
    )
    
    Spacer(modifier = Modifier.weight(1f))
    
    MyTextField(
      value = state.email,
      onValueChange = { viewModel.updateEmail(it) },
      isError = state.emailErrorMessage?.isNotEmpty() ?: false,
      errorMessage = state.emailErrorMessage,
      label = "Email",
      placeholder = "",
      modifier = Modifier.fillMaxWidth(),
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
    )
    
    MyTextField(
      value = state.password,
      onValueChange = { viewModel.updatePassword(it) },
      isError = state.passwordErrorMessage?.isNotEmpty() ?: false,
      errorMessage = state.passwordErrorMessage,
      label = "Password",
      placeholder = "6 or more characters",
      supportingText = "6 or more characters",
      modifier = Modifier.fillMaxWidth(),
      visualTransformation =
        if (state.showPassword) VisualTransformation.None else PasswordVisualTransformation(),
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    )
    
    // Show password switch.
    RememberMeSwitch(
      checked = state.showPassword,
      onCheckedChange = { viewModel.isShowPasswordChecked(state.showPassword) },
    )
    
    // Список типов пользователя.
    var selectedType by remember { mutableStateOf(state.userType) }
    val options = UserType.entries.toTypedArray()
    
    FlySimpleCard(
      content = {
        Text(
          "Choose Account Type",
          style = MedTrackerTheme.typography.title2,
        )
        
        Spacer(modifier = Modifier.padding(8.dp))
        
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          options.forEach { type ->
            Column(verticalArrangement = Arrangement.Center) {
              MyRadioButton(
                selected = selectedType == type,
                onClick = {
                  viewModel.updateUserType(selectedType)
                  selectedType = type
                },
                caption = type.toString().lowercase()
              )
              // Text(text = form.toString().lowercase())
              
            }
          }
        }
      }
    
    )
    
    Spacer(modifier = Modifier.height(16.dp))
    
    val context = LocalContext.current
    
    Row(verticalAlignment = Alignment.CenterVertically) {
      FlyTextButton(onClick = navigateHome) { Text(text = "Cancel") }
      
      Spacer(modifier = Modifier.weight(1f))
      
      FlyButton(onClick = {
        viewModel.onRegisterClick(
          context,
          onSignupSuccess = navigateHome
        )
      }) {
        Text(text = "Create Account")
      }
    }
    
    Spacer(modifier = Modifier.weight(1f))
    
    // просто, чтобы положение полей и кнопок было таким же, как на экране входа.
    FlyTextButton(onClick = {}, enabled = false) { Text(text = "") }
  }
}

/**
 * Composable function that displays a radio button group for selecting user types.
 *
 * This function iterates through available user types and renders a radio button for each type.
 * The selected type is tracked and updated based on user interaction.
 *
 * @param viewModel The SignupScreenViewModel instance providing data and state for the signup screen.
 */
@Composable
fun UserTypesSelection(viewModel: SignupScreenViewModel) {
  var selectedType by remember { mutableStateOf(viewModel.signupScreenState.userType) }
  val options = UserType.entries.toTypedArray()
  
  Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(8.dp),
    horizontalAlignment = Alignment.Start
  ) {
    options.forEach { type ->
      Row {
        MyRadioButton(
          selected = selectedType == type,
          onClick = {
            selectedType = type
          },
          caption = type.toString().lowercase()
        )
      }
      
    }
  }
  
}
