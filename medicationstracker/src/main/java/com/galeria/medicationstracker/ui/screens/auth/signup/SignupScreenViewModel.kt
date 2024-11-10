package com.galeria.medicationstracker.ui.screens.auth.signup

import android.content.Context
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galeria.medicationstracker.SnackbarController
import com.galeria.medicationstracker.SnackbarEvent
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

data class SignupScreenState(
  val email: String = "",
  val emailErrorMessage: String? = null,
  val password: String = "",
  val passwordErrorMessage: String? = null,
  val showPassword: Boolean = false,
)

class SignupScreenViewModel : ViewModel() {

  val auth = FirebaseAuth.getInstance()

  // private lateinit var auth: FirebaseAuth

  var signupScreenState by mutableStateOf(SignupScreenState())

  fun updateEmail(input: String) {
    signupScreenState = signupScreenState.copy(email = input)
  }

  fun updatePassword(input: String) {
    signupScreenState = signupScreenState.copy(password = input)
  }

  fun isShowPasswordChecked(input: Boolean) {
    signupScreenState = signupScreenState.copy(showPassword = !input)
  }

  private fun validateEmail(): Boolean {
    val emailInput = signupScreenState.email.trim()
    var isValid = true
    var errorMessage = ""

    if (emailInput.isBlank() || emailInput.isEmpty()) {
      errorMessage = "Email cannot be empty"
      isValid = false
    } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
      errorMessage = "Wrong email format"
      isValid = false
    }

    signupScreenState = signupScreenState.copy(emailErrorMessage = errorMessage)
    return isValid
  }

  private fun validatePassword(): Boolean {

    val passwordInput = signupScreenState.password
    var isValid = true
    var errorMessage = ""

    if (passwordInput.isBlank() || passwordInput.isEmpty()) {
      errorMessage = "Password cannot be empty"
      isValid = false
    } else if (passwordInput.length < 6) {
      errorMessage = "Password must be at least 6 characters"
      isValid = false
    }

    signupScreenState = signupScreenState.copy(passwordErrorMessage = errorMessage)
    return isValid
  }

  fun onRegisterClick(context: Context, onSignupSuccess: () -> Unit) {
    val isEmailValid = validateEmail()
    val isPasswordValid = validatePassword()

    if (isEmailValid && isPasswordValid) {
      auth
        .createUserWithEmailAndPassword(signupScreenState.email, signupScreenState.password)
        .addOnCompleteListener { task ->
          if (task.isSuccessful) {

            // Toast.makeText(context, "Account Created", Toast.LENGTH_SHORT).show()
            viewModelScope.launch {
              SnackbarController.sendEvent(event = SnackbarEvent(message = "Account Created!"))
            }
            onSignupSuccess.invoke()
          } else {
            viewModelScope.launch {
              SnackbarController.sendEvent(
                event = SnackbarEvent(message = "Account Creation Failed${task.exception?.message}")
              )
            }
          }
        }
    } else {
      viewModelScope.launch {
        SnackbarController.sendEvent(event = SnackbarEvent(message = "Invalid email or password."))
      }
    }
  }
}

/*  fun onRegisterClick(context: Context, onSignupSuccess: () -> Unit) = viewModelScope.launch() {

  if (validatePassword() && validateEmail()) {
    auth
      .createUserWithEmailAndPassword(signupScreenState.email, signupScreenState.password)
      .addOnCompleteListener { task ->
        if (task.isSuccessful) {

          // Toast.makeText(context, "Account Created", Toast.LENGTH_SHORT).show()
          viewModelScope.launch {
            SnackbarController.sendEvent(event = SnackbarEvent(message = "Account Created!"))
          }
          onSignupSuccess.invoke()
        } else {
          viewModelScope.launch {
            SnackbarController.sendEvent(
              event = SnackbarEvent(message = "Account Creation Failed${task.exception?.message}")
            )
          }
        }
      }
  } else {
    viewModelScope.launch {
      SnackbarController.sendEvent(event = SnackbarEvent(message = "Invalid email or password."))
    }
  }
}*/
