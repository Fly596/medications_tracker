package com.galeria.medicationstracker.ui.screens.medications.update

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galeria.medicationstracker.SnackbarController
import com.galeria.medicationstracker.SnackbarEvent
import com.galeria.medicationstracker.data.MedicationForms
import com.galeria.medicationstracker.data.UserMedication
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.DayOfWeek

data class UpdateMedUiState(
  val medName: String = "",
  val medForm: MedicationForms = MedicationForms.TABLET,
  val endDate: String = "",
  val unit: String = "",
  val startDate: String = "",
  val intakeTime: String = "",
  val notes: String = "",
  val strength: String = "",
  val strengthUnit: String = "", // Add strength unit
  val selectedDays: MutableList<DayOfWeek> = mutableListOf(),

  val showDatePicker: Boolean = false,
  val showTimePicker: Boolean = false
)

class UpdateMedVM : ViewModel() {

  var uiState by mutableStateOf(UpdateMedUiState())
    private set

  private val user = Firebase.auth.currentUser
  private val db = Firebase.firestore
  val userId = user?.uid ?: ""

  private var _selectedMedication = MutableStateFlow<UserMedication?>(null)
  var selectedMedication = _selectedMedication.asStateFlow()

  private var _selectedDocumentId = MutableStateFlow<String?>(null)
  var selectedDocumentId = _selectedDocumentId.asStateFlow()

  // для получения выбранного лекарства.
  fun getMedicationFromFirestore(medName: String) {
    db.collection("UserMedication")
      .whereEqualTo("uid", userId)
      .whereEqualTo("name", medName)
      .addSnapshotListener { value, error ->
        if (error != null) {
          return@addSnapshotListener
        }

        if (value != null && value.documents.isNotEmpty()) {
          val document = value.documents[0]
          _selectedMedication.value = document.toObject<UserMedication>()
          _selectedDocumentId.value = document.id // Save the document ID

          //_selectedMedication.value = value.documents[0].toObject()

          if (_selectedMedication.value != null) {
            uiState = uiState.copy(
              medName = _selectedMedication.value!!.name.toString(),
            )
          }
        }
      }
  }

  fun updateMedicationFromFirestore(
  ) {

    val newValues: Map<String, Any?> = mapOf(
      "endDate" to uiState.endDate,
      "form" to uiState.medForm.toString(),
      "frequency" to uiState.selectedDays.toString(),
      "intakeTime" to uiState.intakeTime,
      "name" to uiState.medName,
      "notes" to uiState.notes,
      "strength" to uiState.strength.toFloat(),
      "unit" to uiState.unit.toString(),
      "startDate" to uiState.startDate,
      "uid" to userId
    )

    val documentId = _selectedDocumentId.value
    if (documentId != null) {
      db.collection("UserMedication").document(documentId)
        .update(newValues)
        .addOnSuccessListener {
          viewModelScope.launch {
            SnackbarController.sendEvent(event = SnackbarEvent(message = "Medication updated successfully!"))
          }
          Log.d("Firestore", "Medication updated successfully!")
        }
        .addOnFailureListener { exception ->
          viewModelScope.launch {
            SnackbarController.sendEvent(event = SnackbarEvent(message = "Error updating medication, $exception"))
          }
          Log.e("Firestore", "Error updating medication", exception)
        }
    } else {
      Log.e("Firestore", "No document ID available. Unable to update medication.")
    }

    /*     db.collection("UserMedication")
          .whereEqualTo("uid", userId)
          .whereEqualTo("name", _selectedMedication.value?.name)
          .get()
          .addOnSuccessListener { querySnapshot ->
            if (!querySnapshot.isEmpty) {
              val document = querySnapshot.documents[0]
              val documentRef = document.reference

              documentRef.update(newValues)
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
          } */
  }


  fun updateSelectedDays(input: List<DayOfWeek>) {
    for (day in input) {
      uiState.selectedDays.add(day)
    }
    // val test:String = uiState.selectedDays.toString()

  }

  fun updateMedName(input: String) {
    uiState = uiState.copy(medName = input)
  }

  fun updateMedForm(input: MedicationForms) {
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