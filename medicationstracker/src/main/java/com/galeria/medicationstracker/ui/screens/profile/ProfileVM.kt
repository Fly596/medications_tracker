package com.galeria.medicationstracker.ui.screens.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galeria.medicationstracker.data.User
import com.galeria.medicationstracker.model.FirestoreFunctions.FirestoreService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Source
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ProfileScreenUiState(
  val testData: String = "",
  val user: User? = null,
  val doctors: List<User> = listOf()
)

class ProfileVM : ViewModel() {

  private val _uiState = MutableStateFlow(ProfileScreenUiState())
  val uiState = _uiState.asStateFlow()

  val db = FirestoreService.db
  val firebaseAuth = FirebaseAuth.getInstance()
  val currentUser = firebaseAuth.currentUser
  val currentUserId = firebaseAuth.currentUser?.uid

  init {
    fetchUserData()
    fetchDoctors()
  }

  private fun fetchUserData() {
    viewModelScope.launch {
      val userRef = db.collection("User")
        .document(currentUser?.email.toString())
      val source = Source.CACHE

      try {
        userRef.get(source)
          .addOnSuccessListener { result ->
            val user = result.toObject(User::class.java)
            _uiState.value = _uiState.value.copy(user = user)
          }
          .addOnFailureListener { exp ->
            println("Error fetching user data: ${exp.message}")
          }
      } catch (e: Exception) {
        println("Error fetching user data: ${e.message}")
      }

    }

  }

  private fun fetchDoctors() {
    viewModelScope.launch {
      val userRef = db.collection("User")

      try {
        userRef.whereEqualTo(
          "type",
          "DOCTOR"
        )
          .get()
          .addOnSuccessListener { docs ->
            val doctors = docs.toObjects(User::class.java)

            _uiState.value = _uiState.value.copy(doctors = doctors)
          }
          .addOnFailureListener { exp ->
            Log.d(
              "ProfileVM",
              "Error fetching docs data: ${exp.message}"
            )

          }
      } catch (e: Exception) {
        println("Error fetching docs data: ${e.message}")

      }

    }
  }

  /*     fun showSnackbar(message: String) {
          viewModelScope.launch {
              SnackbarController.sendEvent(
                  event = SnackbarEvent(
                      message = message,
                      action = SnackbarAction(
                          name = "Action",
                          action = SnackbarController.sendEvent(
                              event = SnackbarEvent(
                                  message = "Action performed"
                              )
                          )
                      ),
                  )
              )
          }
      } */
}
