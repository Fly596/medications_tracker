package com.galeria.medicationstracker.ui.screens.autentification.login

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginScreenViewModel : ViewModel() {

    private val _email = MutableStateFlow("")
    val username = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun onSignInClick(email: String, password: String, context: Context, onLoginClick: () -> Unit) {
        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
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
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    /*
        fun onSignUpClick(
            email: String,
            password: String,
            context: Context,
            onSignupClick: () -> Unit
        ) {
            val auth = FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign up success
                        onSignupClick.invoke()
                        Toast.makeText(context, "Account Created", Toast.LENGTH_SHORT).show()
                    } else {
                        // If sign up fails, display a message to the user.
                        Toast.makeText(
                            context,
                            "Sign Up Failed: ${task.exception?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    */

    fun resetPassword(email: String, context: Context) {
        val auth = FirebaseAuth.getInstance()
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Password reset email sent!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(
                        context,
                        "Error sending password reset email: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}