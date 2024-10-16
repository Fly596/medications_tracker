package com.galeria.medicationstracker.ui.screens.autentification.login

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginScreenViewModel : ViewModel() {
  var showPassword by mutableStateOf(false)

  var email by mutableStateOf("")
    private set

  var password by mutableStateOf("")
    private set

  fun updateEmail(input: String) {
    email = input
  }

  fun updatePassword(input: String) {
    password = input
  }

  fun onSignInClick(email: String, password: String, context: Context, onLoginClick: () -> Unit) {
    val auth = FirebaseAuth.getInstance()

    /*    auth.addAuthStateListener { firebaseAuth ->
      val user = firebaseAuth.currentUser
      if (user != null) {
        Log.d("Auth", "User is signed in")
        //onLoginClick.invoke()
      } else {
        Log.d("Auth", "No user is signed in")
      }
    }*/

    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
      if (task.isSuccessful) {
        // Login success
        // onLoginClick.invoke()
        Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
        onLoginClick.invoke()
      } else {
        // If sign in fails, display a message to the user.
        Toast.makeText(
            context,
            "Authentication Failed: ${task.exception?.message}",
            Toast.LENGTH_SHORT,
          )
          .show()
      }
    }
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
