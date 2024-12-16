package com.galeria.medicationstracker.ui.screens.auth.login

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galeria.medicationstracker.SnackbarController
import com.galeria.medicationstracker.SnackbarEvent
import com.galeria.medicationstracker.data.UserTypes
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class LoginScreenState(
  val email: String = "adminka@gmail.com",/* "ggsell@gmail.com" */
  val emailError: String? = null,
  val password: String = "14881337",/* "password" */
  val passwordError: String? = null,
  val showPassword: Boolean = false,
  val userType: UserTypes = UserTypes.PATIENT,
)

class LoginScreenViewModel : ViewModel() {

  val auth = FirebaseAuth.getInstance()
  private val db = Firebase.firestore

  private var _userType = MutableStateFlow<UserTypes?>(null)
  var userType = _userType.asStateFlow()

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

  fun onSignInClick(email: String, password: String, onLoginClick: (userType: UserTypes) -> Unit) {
    val isEmailValid = validateEmail()
    val isPasswordValid = validatePassword()

    if (isEmailValid && isPasswordValid) {
      auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
          if (task.isSuccessful) {
            val userId = task.result?.user?.uid

            if (userId != null) {
              val dataBase = FirebaseFirestore.getInstance()

              dataBase.collection("users")
                .whereEqualTo("uid", userId)
                .whereEqualTo("login", email)
                .get()
                .addOnSuccessListener { snapshot ->
                  if (!snapshot.isEmpty) {
                    val document = snapshot.documents[0]
                    val userTypeString = document.getString("type")

                    if (userTypeString != null) {
                      val docUserType = UserTypes.valueOf(userTypeString.uppercase())
                      loginScreenState = loginScreenState.copy(userType = docUserType)
                      onLoginClick.invoke(docUserType)

                      viewModelScope.launch {
                        SnackbarController.sendEvent(SnackbarEvent("Login Successful!"))
                      }
                    } else {
                      viewModelScope.launch {
                        SnackbarController.sendEvent(
                          event = SnackbarEvent("User data not found.")
                        )
                      }
                    }
                  }

                }.addOnFailureListener { exception ->
                  viewModelScope.launch {
                    SnackbarController.sendEvent(
                      event = SnackbarEvent("Error fetching user data: ${exception.message}")
                    )
                  }
                }


            } else {
              viewModelScope.launch {
                SnackbarController.sendEvent(
                  event = SnackbarEvent("User ID is null. Login failed.")
                )
              }
            }
          } else {
            val errorMessage = when (task.exception) {
              is FirebaseAuthInvalidUserException -> "Invalid email or password."
              is FirebaseAuthInvalidCredentialsException -> "Invalid password."
              else -> "Authentication failed: ${task.exception?.message}"
            }

            viewModelScope.launch {
              SnackbarController.sendEvent(event = SnackbarEvent(message = errorMessage))
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