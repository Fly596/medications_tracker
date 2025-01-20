package com.galeria.medicationstracker.ui.componentsOld

import androidx.lifecycle.*
import com.google.firebase.*
import com.google.type.*
import kotlinx.coroutines.flow.*

data class InputDialogUiState(
    val dateTimeInput: Timestamp = Timestamp.now(),
    val measurementInput: String = "",
)

class InputDialogViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(InputDialogUiState())
    val uiState = _uiState.asStateFlow()

    fun setDateTimeInput(text: Date) {
        //_uiState.value = _uiState.value.copy(dateTimeInput = text.)
    }

    fun setMeasurementInput(text: String) {
        _uiState.value = _uiState.value.copy(measurementInput = text)
    }

    fun addMeasurement() {

    }

}
