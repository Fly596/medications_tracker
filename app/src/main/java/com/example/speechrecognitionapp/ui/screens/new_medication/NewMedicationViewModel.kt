package com.example.speechrecognitionapp.ui.screens.new_medication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class NewMedicationViewModel:ViewModel() {

    var medName by mutableStateOf("")
        private set

    var medType by mutableStateOf("")
        private set

    fun updateMedName(name: String) {
        medName = name
    }

    fun updateMedType(type: String) {
        medType = type
    }

}