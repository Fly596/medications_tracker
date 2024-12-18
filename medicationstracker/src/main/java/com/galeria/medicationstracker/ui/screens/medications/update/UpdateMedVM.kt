package com.galeria.medicationstracker.ui.screens.medications.update

import android.content.*
import android.util.*
import android.widget.*
import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.galeria.medicationstracker.data.*
import com.galeria.medicationstracker.model.FirestoreFunctions.FirestoreService.db
import com.google.firebase.*
import com.google.firebase.auth.*
import com.google.firebase.firestore.*
import com.google.firebase.firestore.Filter
import dagger.hilt.android.lifecycle.*
import kotlinx.coroutines.flow.*
import java.time.*
import javax.inject.*

data class UpdateMedUiState(
    val medName: String = "",
    val medForm: MedicationForms = MedicationForms.TABLET,
    val endDate: Timestamp = Timestamp.now(),
    val unit: String = "",
    val startDate: Timestamp = Timestamp.now(),
    val intakeTime: String = "",
    val notes: String = "",
    val strength: String = "",
    val strengthUnit: String = "", // Add strength unit
    val selectedDays: MutableList<DayOfWeek> = mutableListOf(),

    val showDatePicker: Boolean = false,
    val showTimePicker: Boolean = false
)

@HiltViewModel
class UpdateMedVM @Inject constructor() : ViewModel() {

  var uiState by mutableStateOf(UpdateMedUiState())
    private set

  val currentUserId = FirebaseAuth.getInstance().currentUser?.uid

  private var _selectedMedication = MutableStateFlow<UserMedication?>(null)
  var selectedMedication = _selectedMedication.asStateFlow()

  private var _selectedDocumentId = MutableStateFlow<String?>(null)
  var selectedDocumentId = _selectedDocumentId.asStateFlow()

  // для получения выбранного лекарства.
  fun fetchSelectedMedication(medName: String) {
    db.collection("UserMedication")
        .where(
          Filter.and(
            Filter.equalTo("uid", currentUserId),
            Filter.greaterThanOrEqualTo("name", medName)
          )
        )
        .addSnapshotListener { value, error ->
          if (error != null) {
            return@addSnapshotListener
          }

          if (value != null && value.documents.isNotEmpty()) {
            val document = value.documents[0]
            _selectedMedication.value = document.toObject<UserMedication>()
            _selectedDocumentId.value = document.id // Save the document ID

            if (_selectedMedication.value != null) {
              uiState = uiState.copy(
                medName = _selectedMedication.value!!.name.toString(),
              )
            }
          }
        }
  }

  fun updateMedicationFromFirestore(
      context: Context,
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
      "uid" to currentUserId
    )

    val documentId = _selectedDocumentId.value
    if (documentId != null) {
      db.collection("UserMedication").document(documentId)
          .update(newValues)
          .addOnSuccessListener {
            Toast.makeText(
              context,
              "Medication updated successfully!",
              Toast.LENGTH_SHORT
            ).show()
            /* viewModelScope.launch {
              SnackbarController.sendEvent(event = SnackbarEvent(message = "Medication updated successfully!"))
            } */
            Log.d("Firestore", "Medication updated successfully!")
          }
          .addOnFailureListener { exception ->
            Toast.makeText(context, "Error updating medication", Toast.LENGTH_SHORT)
                .show()

            /*         viewModelScope.launch {
                      SnackbarController.sendEvent(event = SnackbarEvent(message = "Error updating medication, $exception"))
                    } */
            Log.e("Firestore", "Error updating medication", exception)
          }
    } else {
      Log.e("Firestore", "No document ID available. Unable to update medication.")
    }
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

  fun updateEndDate(date: Timestamp?) {
    uiState = uiState.copy(endDate = date ?: Timestamp.now())
  }

  fun updateStartDate(date: Timestamp?) {
    uiState = uiState.copy(startDate = date ?: Timestamp.now())
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