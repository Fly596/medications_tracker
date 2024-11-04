package com.galeria.medicationstracker.ui.screens.autentification.signup

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

data class SignupScreenState(
  val email: String = "",
  val password: String = "",
  val showPassword: Boolean = false
)

class SignupScreenViewModel : ViewModel() {

  private lateinit var auth: FirebaseAuth

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

  /**
   * Handles the registration process when the "Register" button is clicked.
   *
   * This function attempts to create a new user account using Firebase Authentication with the
   * provided email and password. If successful, it displays a success message and invokes the
   * `onSignupClick` callback. If registration fails, it displays an error message with the
   * exception details.
   *
   * **Note:** Currently, user information is not added to the database after successful
   * registration. This is marked as a TODO and should be implemented in the future.
   *
   * @param email The email address of the new user.
   * @param password The password for the new user.
   * @param context The application context used for displaying Toast messages.
   * @param onSignupSuccess A callback function to be invoked after successful registration.
   */
  //
  fun onRegisterClick(
    context: Context,
    onSignupSuccess: () -> Unit,
  ) {
    auth = FirebaseAuth.getInstance()

    auth.createUserWithEmailAndPassword(signupScreenState.email, signupScreenState.password)
      .addOnCompleteListener { task ->
        if (task.isSuccessful) {
          // TODO: Add user info to database.
          Toast.makeText(context, "Account Created", Toast.LENGTH_SHORT).show()
          onSignupSuccess.invoke()
        } else {
          Toast.makeText(context, "Sign Up Failed: ${task.exception?.message}", Toast.LENGTH_SHORT)
            .show()
        }
      }
  }
}
