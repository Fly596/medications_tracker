package com.galeria.medicationstracker.ui.screens.autentification.create_account

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignupScreenViewModel : ViewModel() {

    //private val _uiState = MutableStateFlow(SignupScreenUiState())
    //val uiState: StateFlow<SignupScreenUiState> = _uiState.asStateFlow()

    private lateinit var auth: FirebaseAuth

    private var db = Firebase.firestore

    private val _email = MutableStateFlow("")
    val username = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()


    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    private val _userId = MutableStateFlow("")

    fun updateEmail(newEmail: String) {
        _email.value =  newEmail

    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }
    fun updateUserName(newUserName: String) {
        _userName.value = newUserName
    }

    fun registrateUser(
        email: String,
        password: String,
        context: Context,
        onSignupClick: () -> Unit
    ) {
        auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(
            _email.toString(),
            _password.toString()
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // TODO: Add user info to database.
                Toast.makeText(context, "Account Created", Toast.LENGTH_SHORT).show()
                _userId.value = auth.currentUser?.uid.toString()
                //onSignupClick.invoke()
            } else {
                Toast.makeText(
                    context, "Sign Up Failed: ${task.exception?.message}", Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }

    /*     fun addUserInfo(userId: String) {

            val user =
                hashMapOf(
                    "name" to _userName.value, "email" to _email.value, "password" to _password.value)

            db.collection("users")
                .document(userId)
                .set(user)
                .addOnSuccessListener {
                    Log.d(TAG, "User with ID ${userId} successfully added to database.")
                }
                .addOnFailureListener {
                    Log.d(TAG, "Failed to add user with ID ${userId} to database.")
                }
        } */
}

data class SignupScreenUiState(
    val email: String = "",
    val password: String = "",
    val userId: String = "",
)