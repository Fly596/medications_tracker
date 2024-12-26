package com.galeria.medicationstracker.ui.doctor.patients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galeria.medicationstracker.data.User
import com.galeria.medicationstracker.model.FirestoreFunctions.FirestoreService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

data class PatientsListUiState(
  val patients: List<User> = emptyList()
)

class PatientsListViewModel : ViewModel() {

  private val db = FirestoreService.db

  private val _uiState = MutableStateFlow(PatientsListUiState())
  val uiState = _uiState

  init {
    fetchPatients()
  }

  private fun fetchPatients() {

    viewModelScope.launch {
      val docRef = db.collection("User")

      docRef
        .whereEqualTo("type", "PATIENT")
        .get()
        .addOnSuccessListener { result ->
          val patients = result.toObjects(User::class.java)

          _uiState.value = _uiState.value.copy(patients = patients)
        }
        .addOnFailureListener { exception ->
          println("Error finding documents: $exception")
        }
    }

  }
}