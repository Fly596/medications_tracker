package com.galeria.medicationstracker.ui.screens.autentification.login

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galeria.medicationstracker.SnackbarAction
import com.galeria.medicationstracker.SnackbarController
import com.galeria.medicationstracker.SnackbarEvent
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

data class LoginScreenState(
  val email: String = "ggsell@gmail.com",
  val password: String = "password",
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

  fun onSignInClick(email: String, password: String, context: Context, onLoginClick: () -> Unit) {

    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
      if (task.isSuccessful) {
        // Login success
        Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
        onLoginClick.invoke() // open main screen.
      } else {
        // Login failed.
        viewModelScope.launch {
          SnackbarController.sendEvent(
            event =
              SnackbarEvent(
                message = "Authentication Failed: ${task.exception?.message}",
                action =
                  SnackbarAction(
                    name = "U Can Cry",
                    action = {
                      SnackbarController.sendEvent(
                        event = SnackbarEvent(message = "Snackbar Action")
                      )
                    },
                  ),
              )
          )
        }
      }
    } //
  }

  fun resetPassword(email: String, context: Context) {
    val auth = FirebaseAuth.getInstance()
    auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
      if (task.isSuccessful) {
        Toast.makeText(context, "Password reset email sent!", Toast.LENGTH_SHORT).show()
      } else {
        Toast.makeText(
            context,
            "Error sending password reset email: ${task.exception?.message}",
            Toast.LENGTH_SHORT,
          )
          .show()
      }
    }
  }
}
