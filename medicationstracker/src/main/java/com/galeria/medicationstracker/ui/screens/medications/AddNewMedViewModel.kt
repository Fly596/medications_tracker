package com.galeria.medicationstracker.ui.screens.medications

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.galeria.medicationstracker.data.Frequency
import com.galeria.medicationstracker.data.Medication
import com.galeria.medicationstracker.data.MedicationForms
import com.galeria.medicationstracker.data.MedicationUnit
import com.google.firebase.Firebase
import com.google.firebase.appcheck.internal.util.Logger.TAG
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import java.time.LocalTime

data class NewMedicationUiState(
  val uid: String = "",
  val name: String = "",
  val type: String = "",
  var form: MedicationForms = MedicationForms.TABLET, // f
  val strength: String = "",
  val unit: MedicationUnit = MedicationUnit.MG, // f
  val startDate: String = "",// f
  val endDate: String = "",// f
  val frequency: Frequency = Frequency.AtRegularIntervals(),// f
  val intakeTime: LocalTime = LocalTime.now(),// f
  val notes: String = "",
  val showDatePicker: Boolean = false,
  val showTimePicker: Boolean = false
)

class AddNewMedViewModel : ViewModel() {

  var uiState by mutableStateOf(NewMedicationUiState())
    private set

  private val db = Firebase.firestore
  private val user = Firebase.auth.currentUser
  val userId = user?.uid ?: ""

  var selectedStartDate by mutableStateOf<Long?>(null)
  // private set

  var selectedEndDate by mutableStateOf<Long?>(null)

  // private set
  var combinedDates = Pair<Long?, Long?>(selectedStartDate, selectedEndDate)

  // TODO: Check for dublicates.
  fun addMedication(
    /* medication: Medication, */
    context: Context,
  ) {
    val newUserMedication =
      Medication(
        userId,
        uiState.name,
        uiState.type,
        uiState.form,
        uiState.strength.toFloat(),
        uiState.unit
      )

    db.collection("med_temp").add(newUserMedication)
      .addOnSuccessListener {
        Toast.makeText(
          context,
          "DocumentSnapshot added successfully!",
          Toast.LENGTH_SHORT
        ).show()

        Log.d(TAG, "DocumentSnapshot added with ID: ${it.id}")
      }
      .addOnFailureListener { e ->
        Toast.makeText(context, "Error adding medication", Toast.LENGTH_SHORT).show()

        Log.w(TAG, "Error adding document", e)
      }
  }


  fun isShowDateChecked(input: Boolean) {
    uiState.copy(showDatePicker = !input)
  }
  fun isShowTimeChecked(input: Boolean) {
    uiState.copy(showTimePicker =  !input)
  }

  // region fields data
  fun updateStartDate(input: String) {
    uiState = uiState.copy(startDate = input)
  }

  fun updateEndDate(input: String) {
    uiState = uiState.copy(endDate = input)
  }

  fun updateMedName(newName: String) {
    uiState = uiState.copy(name = newName)
  }

  fun updateMedType(newType: String) {
    uiState = uiState.copy(type = newType)
  }

  fun updateMedForm(newForm: MedicationForms) {
    uiState = uiState.copy(form = newForm)
  }

  fun updateMedStrength(newStrength: String) {
    uiState = uiState.copy(strength = newStrength)
  }

  fun updateMedUnit(newUnit: MedicationUnit) {
    uiState = uiState.copy(unit = newUnit)
  }

  fun updateMedFrequency(newFrequency: Frequency) {
    uiState = uiState.copy(frequency = newFrequency)
  }

  fun updateIntakeTime(newTime: LocalTime) {
    uiState = uiState.copy(intakeTime = newTime)
  }

  fun updateMedNotes(newNotes: String) {
    uiState = uiState.copy(notes = newNotes)
  }
  /*   fun updateStartDate(newStartDate: LocalDate) {
      uiState = uiState.copy(startDate = newStartDate.toString())
    }

    fun updateEndDate(newEndDate: LocalDate) {
      uiState = uiState.copy(endDate = newEndDate.toString())
    } */

  // endregion

}
