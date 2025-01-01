package com.galeria.medicationstracker.ui.screens.profile.appoinment

import androidx.lifecycle.ViewModel
import com.google.firebase.Timestamp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class AppointmentUiState(
    val date: Timestamp = Timestamp.now(),
    val time: String = "",
    val doctorId: String = "",
    val patientId: String = "",
)

class AppointmentViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AppointmentUiState())
    val uiState = _uiState.asStateFlow()

    fun updateDate(input: Timestamp) {
        _uiState.value = _uiState.value.copy(date = input)
    }

    fun updateTime(input: String) {
        _uiState.value = _uiState.value.copy(time = input)
    }
}