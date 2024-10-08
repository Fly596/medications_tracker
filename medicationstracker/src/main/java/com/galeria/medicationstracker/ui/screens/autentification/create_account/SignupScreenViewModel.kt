package com.galeria.medicationstracker.ui.screens.autentification.create_account

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.appcheck.internal.util.Logger.TAG
import com.google.firebase.auth.FirebaseAuth
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

    /**
     * Handles the registration process when the "Register" button is clicked.
     *
     * This function attempts to create a new user account using Firebase Authentication
     * with the provided email and password. If successful, it displays a success message
     * and invokes the `onSignupClick` callback. If registration fails, it displays
     * an error message with the exception details.
     *
     * **Note:** Currently, user information is not added to the database after successful registration.
     * This is marked as a TODO and should be implemented in the future.
     *
     * @param email The email address of the new user.
     * @param password The password for the new user.
     * @param context The application context used for displaying Toast messages.
     * @param onSignupClick A callback function to be invoked after successful registration.
     *///
    fun onRegisterClick(
        email: String,
        password: String,
        context: Context,
        onSignupClick: () -> Unit
    ) {
        auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // TODO: Add user info to database.
                Toast.makeText(context, "Account Created", Toast.LENGTH_SHORT).show()
                onSignupClick.invoke()
            } else {
                Toast.makeText(
                        context, "Sign Up Failed: ${task.exception?.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    // TODO: check if it works
    fun addUserInfo(userId: String) {
        val user =
            hashMapOf(
                "name" to _userName.value, "email" to _email.value, "password" to _password.value)

        db.collection("users")
            .document(userId)
            .set(user)
            .addOnSuccessListener {
                Log.d(TAG, "User with ID $userId successfully added to database.")
            }
            .addOnFailureListener {
                Log.d(TAG, "Failed to add user with ID $userId to database.")
            }
    }
}
