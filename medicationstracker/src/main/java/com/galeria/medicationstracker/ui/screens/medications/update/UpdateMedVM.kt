package com.galeria.medicationstracker.ui.screens.medications.update

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.galeria.medicationstracker.data.UserMedication
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class UpdateMedUiState(
  val medName: String = "",
  val medForm: String = "",
  val endDate: String = "",
  val startDate: String = "",
  val intakeTime: String = "",
  val notes: String = "",
  val strength: String = "",
  val strengthUnit: String = "", // Add strength unit

  val showDatePicker: Boolean = false,
  val showTimePicker: Boolean = false
)

class UpdateMedVM : ViewModel() {

  private val user = Firebase.auth.currentUser
  private val db = Firebase.firestore

  private var _selectedMedication = MutableStateFlow<UserMedication?>(null)
  var selectedMedication = _selectedMedication.asStateFlow()

  fun updateMedicationFromFirestore(
    medication: UserMedication,
    updateValues: Map<String, Any>
  ) {

    db.collection("UserMedication")
      .whereEqualTo("name", medication?.name)
      .get()
      .addOnSuccessListener { querySnapshot ->
        if (!querySnapshot.isEmpty) {
          val document = querySnapshot.documents[0]
          val documentRef = document.reference

          documentRef.update(updateValues)
            .addOnSuccessListener {
              Log.d("Firestore", "Medication updated successfully!")
            }
            .addOnFailureListener { exception ->
              Log.e("Firestore", "Error updating medication", exception)
            }
          //_selectedMedication.value = document.toObject()
        } else {
          Log.e("Firestore", "No matching medication found")
        }
      }
      .addOnFailureListener { exception ->
        Log.e("Firestore", "Error querying medication", exception)
      }
  }

  var uiState by mutableStateOf(UpdateMedUiState())
    private set

  fun updateMedName(input: String) {
    uiState = uiState.copy(medName = input)
  }

  fun updateMedForm(input: String) {
    uiState = uiState.copy(medForm = input)
  }

  fun updateEndDate(date: String) {
    uiState = uiState.copy(endDate = date)
  }

  fun updateStartDate(date: String) {
    uiState = uiState.copy(startDate = date)
  }

  fun updateIntakeTime(time: String) {
    uiState = uiState.copy(intakeTime = time)
  }

  fun updateNotes(input: String) {
    uiState = uiState.copy(notes = input)
  }

  fun updateStrength(input: String) {
    uiState = uiState.copy(strength = input)
  }

  fun updateStrengthUnit(input: String) {
    uiState = uiState.copy(strengthUnit = input)
  }
}