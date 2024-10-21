package com.galeria.medicationstracker.ui.screens.medications

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galeria.medicationstracker.data.Frequency
import com.galeria.medicationstracker.data.MedicationForm
import com.galeria.medicationstracker.data.MedicationUnit
import com.galeria.medicationstracker.data.TEMP_Medication
import com.google.firebase.Firebase
import com.google.firebase.appcheck.internal.util.Logger.TAG
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.launch
import java.time.LocalTime

data class MedicationsUiState(
  val uid: String = "",
  val name: String = "",
  val type: String = "",
  val form: MedicationForm = MedicationForm.TABLET, // f
  val strength: Float = 0.0f,
  val unit: MedicationUnit = MedicationUnit.MG, // f
  val startDate: String = "",// f
  val endDate: String= "",// f
  val frequency: Frequency = Frequency.AtRegularIntervals(0),// f
  val intakeTime: LocalTime = LocalTime.now(),// f
  val notes: String = "",
)

class MedicationsViewModel : ViewModel() {

  var uiState by mutableStateOf(MedicationsUiState())
    private set

  var userMedications =
    mutableStateOf<List<TEMP_Medication>>(emptyList())
    private set
  //val userMedications = _userMedications.asStateFlow()

  var showDatePicker by mutableStateOf(false)

  var selectedStartDate by mutableStateOf<Long?>(null)
  // private set

  var selectedEndDate by mutableStateOf<Long?>(null)
  // private set

  var combinedDates = Pair<Long?, Long?>(selectedStartDate, selectedEndDate)

  private val db = Firebase.firestore
  private val user = Firebase.auth.currentUser
  val userId = user?.uid ?: ""

  init {
    getMedsList()
  }

  fun getMedsList() {
    viewModelScope.launch {
      try {
        db.collection("med_temp")
          .whereEqualTo("uid", userId)
          .addSnapshotListener { snapshot, error ->
            if (error != null) {
              return@addSnapshotListener
            }
            if (snapshot != null) {
              userMedications.value = snapshot.toObjects()
            }
          }
      } catch (e: Exception) {
        Log.d(TAG, "Error getting documents: ", e)
      }
    }
  }

  // TODO: Check for dublicates.
  fun addMedication(
    medication: TEMP_Medication,
    context: Context,
  ) {
    db.collection("med_temp").add(medication)
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

  fun updateMedForm(newForm: MedicationForm) {
    uiState = uiState.copy(form = newForm)
  }

  fun updateMedStrength(newStrength: Float) {
    uiState = uiState.copy(strength = newStrength)
  }

  fun updateMedUnit(newUnit: MedicationUnit) {
    uiState = uiState.copy(unit = newUnit)
  }

  /*   fun updateStartDate(newStartDate: LocalDate) {
      uiState = uiState.copy(startDate = newStartDate.toString())
    }

    fun updateEndDate(newEndDate: LocalDate) {
      uiState = uiState.copy(endDate = newEndDate.toString())
    } */

  fun updateMedFrequency(newFrequency: Frequency) {
    uiState = uiState.copy(frequency = newFrequency)
  }

  fun updateIntakeTime(newTime: LocalTime) {
    uiState = uiState.copy(intakeTime = newTime)
  }

  fun updateMedNotes(newNotes: String) {
    uiState = uiState.copy(notes = newNotes)
  }
  // endregion


}


/*  fun getMedsList() {

    // TODO: get meds from firebase.
    db.collection("med_temp").addSnapshotListener { value, error ->
      if (error != null) {
        return@addSnapshotListener
      }

      if (value != null) {
        _userMedications.value = value.toObjects()
      }
    }*/
