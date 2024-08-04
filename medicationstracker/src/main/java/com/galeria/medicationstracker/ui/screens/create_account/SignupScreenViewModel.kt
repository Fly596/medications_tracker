package com.galeria.medicationstracker.ui.screens.create_account

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.appcheck.internal.util.Logger.TAG
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignupScreenViewModel : ViewModel() {

    private lateinit var auth: FirebaseAuth

    private var db = Firebase.firestore

    private val _userId = MutableStateFlow(-1)
    val userId = _userId.asStateFlow()

    private val _email = MutableStateFlow("")
    val username = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun updateUserName(newUserName: String) {
        _userName.value = newUserName
    }

    fun getUserId(user: FirebaseUser?) {
    }

    fun onRegisterClick(
        email: String,
        password: String,
        userName: String,
        context: Context,
        onSignupClick: () -> Unit
    ) {
        auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // onSignInClick(email, password)

                    // Sign up success
                    // onSignupClick.invoke()
                    // TODO: Add user info to database.
                    Toast.makeText(context, "Account Created", Toast.LENGTH_SHORT).show()
                    onSignupClick.invoke()
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

    fun addUserInfo(userId: String) {

        val user = hashMapOf(
            "name" to _userName.value,
            "email" to _email.value,
            "password" to _password.value
        )

        db.collection("users").document(userId).set(user).addOnSuccessListener {
            Log.d(TAG, "User with ID ${userId} successfully added to database.")
        }
            .addOnFailureListener {
                Log.d(TAG, "Failed to add user with ID ${userId} to database.")
            }

    }

}