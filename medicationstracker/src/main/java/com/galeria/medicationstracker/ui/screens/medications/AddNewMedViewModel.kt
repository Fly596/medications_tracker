package com.galeria.medicationstracker.ui.screens.medications

import android.content.*
import android.util.*
import android.widget.*
import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.galeria.medicationstracker.data.*
import com.google.firebase.*
import com.google.firebase.appcheck.internal.util.Logger.TAG
import com.google.firebase.auth.*
import com.google.firebase.firestore.*
import java.time.*

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
  val selectedDays: MutableList<DayOfWeek> = mutableListOf(),
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
  // var combinedDates = Pair<Long?, Long?>(selectedStartDate, selectedEndDate)
  
  fun isShowDateChecked(input: Boolean) {
    uiState.copy(showDatePicker = !input)
  }
  
  fun isShowTimeChecked(input: Boolean) {
    uiState.copy(showTimePicker = !input)
  }
  
  fun updateSelectedDays(input: List<DayOfWeek>) {
    for (day in input) {
      uiState.selectedDays.add(day)
    }
    // val test:String = uiState.selectedDays.toString()
    
  }
  
  // TODO: Check for dublicates.
  fun addMedication(
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
        uiState.selectedDays.toString(),
        uiState.medIntakeTime,
        uiState.medNotes
      )
    
    db.collection("UserMedication").add(newUserMedication)
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
  }
  
  // region fields data
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
  
  /*   fun updateMedType(newType: String) {
      uiState = uiState.copy(type = newType)
    } */
  
  /*   fun updateMedFrequency(newFrequency: Frequency) {
      uiState = uiState.copy(frequency = newFrequency)
    } */
  
  /*   fun updateStartDate(newStartDate: LocalDate) {
      uiState = uiState.copy(startDate = newStartDate.toString())
    }

    fun updateEndDate(newEndDate: LocalDate) {
      uiState = uiState.copy(endDate = newEndDate.toString())
    } */
  
  // endregion
  
}
