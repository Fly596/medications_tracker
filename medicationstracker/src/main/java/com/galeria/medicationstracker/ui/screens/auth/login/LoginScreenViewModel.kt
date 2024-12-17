package com.galeria.medicationstracker.ui.screens.auth.login

import android.util.*
import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.galeria.medicationstracker.*
import com.galeria.medicationstracker.data.*
import com.google.firebase.*
import com.google.firebase.auth.*
import com.google.firebase.firestore.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.*

data class LoginScreenState(
  val email: String = "user@gmail.com",/* "ggsell@gmail.com", *//* "adminka@gmail.com" */
  val emailError: String? = null,
  val password: String = "user66",/*"password" "14881337" */
  val passwordError: String? = null,
  val showPassword: Boolean = false,
  val userType: UserType = UserType.PATIENT,
)

class LoginScreenViewModel : ViewModel() {
  
  val auth = FirebaseAuth.getInstance()
  private val db = Firebase.firestore
  
  private var _userType = MutableStateFlow<String?>(null)
  var userType = _userType.asStateFlow()
  
  var loginScreenState by mutableStateOf(LoginScreenState())
  
  fun getUserType(login: String? = null) {
    viewModelScope.launch {
      try {
        if (login == null) {
          _userType.value = null
          return@launch
        }
        
        val snapshot = db.collection("users")
          .whereEqualTo("login", login)
          .limit(1)
          .get()
          .await()
        
        if (!snapshot.isEmpty) {
          _userType.value = snapshot.documents[0].get("type") as? String
        } else {
          _userType.value = null
        }
        
        
      } catch (exception: Exception) {
        Log.e("MainViewModel", "Error getting user type", exception)
        // Handle error
      }
    }
    
    /*     val collectionRef = db.collection("users")
        
        collectionRef.whereEqualTo("login", login)
          .get()
          .addOnSuccessListener { docs ->
            for (doc in docs) {
              val ttype = doc.get("type")
              _userType.value = ttype.toString()
            }
          } */
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
  
  fun onSignInClick(
    email: String,
    password: String,
    onLoginClick: (userType: UserType) -> Unit
  ) {
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
                      val docUserType =
                        UserType.valueOf(userTypeString.uppercase())
                      loginScreenState =
                        loginScreenState.copy(userType = docUserType)
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
  
  
  fun updateEmail(input: String) {
    loginScreenState = loginScreenState.copy(email = input)
  }
  
  fun updatePassword(input: String) {
    loginScreenState = loginScreenState.copy(password = input)
  }
  
  fun isShowPasswordChecked(input: Boolean) {
    loginScreenState = loginScreenState.copy(showPassword = !input)
  }
  
}