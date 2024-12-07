package com.galeria.medicationstracker.ui.screens.medications.update

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

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