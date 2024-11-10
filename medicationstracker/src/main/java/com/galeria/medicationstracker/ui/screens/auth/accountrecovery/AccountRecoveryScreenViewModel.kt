package com.galeria.medicationstracker.ui.screens.auth.accountrecovery

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

data class AccountRecoveryScreenState(
  val email: String = "",
  val emailError: String? = null
)

class AccountRecoveryScreenViewModel : ViewModel() {

  val auth = FirebaseAuth.getInstance()

  var accountRecoveryScreenState by mutableStateOf(AccountRecoveryScreenState())

  fun updateEmail(input: String) {
    accountRecoveryScreenState = accountRecoveryScreenState.copy(email = input)
  }

  private fun validateEmail(): Boolean {
    val emailInput = accountRecoveryScreenState.email.trim()
    var isValid = true
    var errorMessage = ""

    if (emailInput.isBlank() || emailInput.isEmpty()) {
      errorMessage = "Email cannot be empty"
      isValid = false
    } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
      errorMessage = "Wrong email format"
      isValid = false
    }

    accountRecoveryScreenState =
      accountRecoveryScreenState.copy(emailError = errorMessage)
    return isValid
  }

  fun resetPassword(email: String) {
    val isEmailValid = validateEmail()

    if (isEmailValid) {
      auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
        if (task.isSuccessful) {
          // Password reset email has been sent.
          viewModelScope.launch {
            SnackbarController.sendEvent(event = SnackbarEvent("Password reset email sent!"))
          }
        } else {
          // Error sending the password reset email.
          viewModelScope.launch {
            SnackbarController.sendEvent(
              event =
              SnackbarEvent("Error sending password reset email: ${task.exception?.message}")
            )
          }
        }
      }
    }
  }
}
