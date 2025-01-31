package com.galeria.medicationstracker.ui.screens.medications.newmed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galeria.medicationstracker.data.MedicationForm
import com.galeria.medicationstracker.data.MedicationUnit
import com.galeria.medicationstracker.utils.FirestoreFunctions.FirestoreService
import com.google.firebase.Timestamp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class NewMedicationState(
    val medName: String = "",
    val dosage: String = "",
    val form: String = MedicationForm.TABLET.toString().replaceFirstChar { it.uppercase() },
    val unit: String = MedicationUnit.MG.toString().replaceFirstChar { it.uppercase() },
    val startDate: Timestamp = Timestamp.now(),
    val endDate: Timestamp = Timestamp.now(),
    val time: String = "",
    val weekDays: List<String> = emptyList()
)

class NewMedicationViewModel : ViewModel() {
    
    private val _state = MutableStateFlow(NewMedicationState())
    val state = _state.asStateFlow()
    val db = FirestoreService.db
    
    fun addMedicationToDataBase(){
        viewModelScope.launch {
            // TODO: implement this shi
        }
    }
    
    fun updateName(input: String) {
        _state.value = _state.value.copy(medName = input)
    }
    
    fun updateDosage(input: String) {
        _state.value = _state.value.copy(dosage = input)
    }
    
    fun updateForm(input: String) {
        _state.value = _state.value.copy(form = input)
    }
    
    fun updateUnit(input: String) {
        _state.value = _state.value.copy(unit = input)
    }
    
    fun updateStartDate(input: Timestamp) {
        _state.value = _state.value.copy(startDate = input)
    }
    
    fun updateEndDate(input: Timestamp) {
        _state.value = _state.value.copy(endDate = input)
    }
    
    fun updateTime(input: String) {
        _state.value = _state.value.copy(time = input)
    }
    
    fun updateWeekDays(input: List<String>) {
        _state.value = _state.value.copy(weekDays = input)
    }
    
    
}