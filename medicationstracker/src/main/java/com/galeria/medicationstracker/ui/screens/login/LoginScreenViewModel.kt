package com.galeria.medicationstracker.ui.screens.login

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galeria.medicationstracker.SnackbarController
import com.galeria.medicationstracker.SnackbarEvent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.launch

data class LoginScreenState(
  val email: String = "ggsell@gmail.com",
  val emailError: String? = null,
  val password: String = "password",
  val passwordError: String? = null,
  val showPassword: Boolean = false,
)

class LoginScreenViewModel : ViewModel() {

  val auth = FirebaseAuth.getInstance()

  var loginScreenState by mutableStateOf(LoginScreenState())

  fun updateEmail(input: String) {
    loginScreenState = loginScreenState.copy(email = input)
  }

  fun updatePassword(input: String) {
    loginScreenState = loginScreenState.copy(password = input)
  }

  fun isShowPasswordChecked(input: Boolean) {
    loginScreenState = loginScreenState.copy(showPassword = !input)
  }

  private fun validateEmail(): Boolean {
    val emailInput = loginScreenState.email.trim()
    var isValid = true
    var errorMessage = ""

    if (emailInput.isBlank() || emailInput.isEmpty()) {
      errorMessage = "Email cannot be empty"
      isValid = false
    } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
      errorMessage = "Wrong email format"
      isValid = false
    }

    loginScreenState = loginScreenState.copy(emailError = errorMessage)
    return isValid
  }

  private fun validatePassword(): Boolean {
    val passwordInput = loginScreenState.password
    var isValid = true
    var errorMessage = ""

    if (passwordInput.isBlank() || passwordInput.isEmpty()) {
      errorMessage = "Password cannot be empty"
      isValid = false
    } else if (passwordInput.length < 6) {
      errorMessage = "Password must be at least 6 characters"
      isValid = false
    }

    loginScreenState = loginScreenState.copy(passwordError = errorMessage)
    return isValid
  }

  fun validateInput(): Boolean {
    return validateEmail() && validatePassword()
  }

  fun onSignInClick(email: String, password: String, onLoginClick: () -> Unit) {

    if (validateInput()) {
      auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
        if (task.isSuccessful) {
          // Login success
          viewModelScope.launch {
            SnackbarController.sendEvent(event = SnackbarEvent("Login Successful!"))
          }
          // Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
          onLoginClick.invoke() // open main screen.
        } else {
          val errorMessage =
            when (task.exception) {
              is FirebaseAuthInvalidUserException -> "Invalid email or password."
              is FirebaseAuthInvalidCredentialsException -> "Invalid password."
              else -> "Authentication failed: ${task.exception?.message}"
            }

          // Login failed.
          viewModelScope.launch {
            SnackbarController.sendEvent(event = SnackbarEvent(message = errorMessage))
          }
        }
      } //
    } else {
      viewModelScope.launch {
        SnackbarController.sendEvent(event = SnackbarEvent(message = "Invalid email or password."))
      }
    }
  }
}
