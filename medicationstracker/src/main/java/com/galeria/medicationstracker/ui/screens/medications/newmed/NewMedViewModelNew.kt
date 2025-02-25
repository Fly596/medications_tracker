package com.galeria.medicationstracker.ui.screens.medications.newmed

import androidx.lifecycle.ViewModel
import com.galeria.medicationstracker.data.MedicationsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class NewMedUiStateNew(
    val uid: String = "",
    val medName: String = "",
    val medForm: String = "",
    val medStrength: Float = 0.0f,
    val medUnit: String = "",
    val medStartDate: String = "",
    val medEndDate: String = "",
    val medIntakeTime: String = "",
    val medNotes: String = "",
    val intakeDays: List<String> = emptyList(),
)


class NewMedViewModelNew @Inject constructor(
    private val repository: MedicationsRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(NewMedUiStateNew())
    val uiState: StateFlow<NewMedUiStateNew> = _uiState.asStateFlow()
}