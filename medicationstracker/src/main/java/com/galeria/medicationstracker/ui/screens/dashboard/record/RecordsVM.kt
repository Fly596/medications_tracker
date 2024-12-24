package com.galeria.medicationstracker.ui.screens.dashboard.record

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.galeria.medicationstracker.data.UserIntake
import com.galeria.medicationstracker.model.FirestoreFunctions.FirestoreService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class RecordsUiState(
    val intakes: List<UserIntake> = listOf()
)

class RecordsVM : ViewModel() {

  private val _intakesData = MutableStateFlow<List<UserIntake>>(emptyList())
  val intakesData: StateFlow<List<UserIntake>> = _intakesData.asStateFlow()

  val db = FirestoreService.db
  val firebaseAuth = FirebaseAuth.getInstance()
  val currentUserId = firebaseAuth.currentUser?.uid

  var uiState by mutableStateOf(RecordsUiState())
    private set

  init {
    fetchUserIntakes()
  }

  fun fetchUserIntakes() {
    val intakesRef = db.collection("MedicationIntake")

    intakesRef.get()
        .addOnSuccessListener { result ->
          var intakes = result.toObjects(UserIntake::class.java)
          _intakesData.value = intakes
          uiState = uiState.copy(intakes = intakes)
        }
        .addOnFailureListener { exp ->
          println("Error fetching intakes: ${exp.message}")
        }

  }

}
