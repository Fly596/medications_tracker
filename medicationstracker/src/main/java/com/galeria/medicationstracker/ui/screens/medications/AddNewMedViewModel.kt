package com.galeria.medicationstracker.ui.screens.medications

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.galeria.medicationstracker.data.MedicationForms
import com.galeria.medicationstracker.data.MedicationUnit
import com.galeria.medicationstracker.data.UserMedicationUpd
import com.galeria.medicationstracker.model.FirestoreFunctions.FirestoreService
import com.google.firebase.Timestamp
import com.google.firebase.appcheck.internal.util.Logger.TAG
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class NewMedicationUiState(
    val uid: String = "",
    val medName: String = "",
    var medForm: MedicationForms = MedicationForms.TABLET, // f
    val medStrength: String = "",
    val medUnit: MedicationUnit = MedicationUnit.MG, // f
    val medStartDate: Timestamp = Timestamp.now(),// f
    val medEndDate: Timestamp = Timestamp.now(),// f
    val medIntakeTime: String = "",// f
    val medNotes: String = "",
    val showDatePicker: Boolean = false,
    val showTimePicker: Boolean = false,
    val selectedDays: List<String> = emptyList(),
)

@HiltViewModel
class AddNewMedViewModel @Inject constructor() : ViewModel() {

  var uiState by mutableStateOf(NewMedicationUiState())
    private set

  val userId = FirebaseAuth.getInstance().currentUser?.uid
  val userLogin = FirebaseAuth.getInstance().currentUser?.email

  var selectedStartDate by mutableStateOf<Long?>(null)
  // private set

  var selectedEndDate by mutableStateOf<Long?>(null)

  // private set
  // var combinedDates = Pair<Long?, Long?>(selectedStartDate, selectedEndDate)

  // TODO: Check for dublicates.
  fun addMedication(
      context: Context,
  ) {
    val medicationRef = FirestoreService.db
        .collection("User").document("$userLogin")
        .collection("drugs")

    val newUserMedication =
      UserMedicationUpd(
        uiState.medName,
        uiState.medForm.toString(),
        uiState.medStrength.toFloat(),
        uiState.medUnit.toString(),
        uiState.medStartDate,
        uiState.medEndDate,
        uiState.selectedDays,
        uiState.medIntakeTime,
        uiState.medNotes
      )


    medicationRef.document(uiState.medName).set(newUserMedication)
        .addOnSuccessListener {
          Toast.makeText(
            context,
            "DocumentSnapshot added successfully!",
            Toast.LENGTH_SHORT
          ).show()

          Log.d(TAG, "DocumentSnapshot added with ID: ${uiState.medName}")
        }
        .addOnFailureListener { e ->
          Toast.makeText(context, "Error adding medication", Toast.LENGTH_SHORT)
              .show()

          Log.w(TAG, "Error adding document", e)
        }

    /*     FirestoreService.db.collection("UserMedication").add(newUserMedication)
            .addOnSuccessListener {
              Toast.makeText(
                context,
                "DocumentSnapshot added successfully!",
                Toast.LENGTH_SHORT
              ).show()

              Log.d(TAG, "DocumentSnapshot added with ID: ${it.id}")
            }
            .addOnFailureListener { e ->
              Toast.makeText(context, "Error adding medication", Toast.LENGTH_SHORT)
                  .show()

              Log.w(TAG, "Error adding document", e)
            } */
  }
  /*   fun addMedication(
      context: Context,
  ) {

    val newUserMedication =
      UserMedication(
        userId,
        uiState.medName,
        uiState.medForm.toString(),
        uiState.medStrength.toFloat(),
        uiState.medUnit.toString(),
        uiState.medStartDate,
        uiState.medEndDate,
        uiState.selectedDays,
        uiState.medIntakeTime,
        uiState.medNotes
      )

    FirestoreService.db.collection("UserMedication").add(newUserMedication)
        .addOnSuccessListener {
          Toast.makeText(
            context,
            "DocumentSnapshot added successfully!",
            Toast.LENGTH_SHORT
          ).show()

          Log.d(TAG, "DocumentSnapshot added with ID: ${it.id}")
        }
        .addOnFailureListener { e ->
          Toast.makeText(context, "Error adding medication", Toast.LENGTH_SHORT)
              .show()

          Log.w(TAG, "Error adding document", e)
        }
  } */

  fun updateStartDate(input: Timestamp?) {

    uiState = uiState.copy(medStartDate = input ?: Timestamp.now())
  }

  fun updateEndDate(input: Timestamp?) {
    uiState = uiState.copy(medEndDate = input ?: Timestamp.now())
  }

  fun updateMedName(newName: String) {
    uiState = uiState.copy(medName = newName)
  }

  fun updateMedForm(newForm: MedicationForms) {
    uiState = uiState.copy(medForm = newForm)
  }

  fun updateMedStrength(newStrength: String) {
    uiState = uiState.copy(medStrength = newStrength/* .toFloat() */)
  }

  fun updateMedUnit(newUnit: MedicationUnit) {
    uiState = uiState.copy(medUnit = newUnit)
  }

  fun updateIntakeTime(newTime: String) {
    uiState = uiState.copy(medIntakeTime = newTime)
  }

  fun updateMedNotes(newNotes: String) {
    uiState = uiState.copy(medNotes = newNotes)
  }

  fun isShowDateChecked(input: Boolean) {
    uiState = uiState.copy(showDatePicker = !input)
  }

  fun isShowTimeChecked(input: Boolean) {
    uiState = uiState.copy(showTimePicker = !input)
  }

  fun updateSelectedDays(input: List<String>) {
    uiState = uiState.copy(selectedDays = uiState.selectedDays + input)
  }

}
