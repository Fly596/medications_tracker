package com.galeria.medicationstracker.ui.screens.new_medication

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galeria.medicationstracker.data.UserMedication
import com.google.firebase.Firebase
import com.google.firebase.appcheck.internal.util.Logger.TAG
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewMedicationViewModel : ViewModel() {

    private val db = Firebase.firestore

    private val _inputName = MutableStateFlow("")
    val inputName: StateFlow<String> = _inputName

    private val _inputForm = MutableStateFlow("")
    val inputForm: StateFlow<String> = _inputForm

    private val _inputStrength = MutableStateFlow("")
    val inputStrength: StateFlow<String> = _inputStrength

    private val _inputUnit = MutableStateFlow("")
    val inputUnit: StateFlow<String> = _inputUnit

    private val _inputFrequency = MutableStateFlow("")
    val inputFrequency: StateFlow<String> = _inputFrequency

    private val _inputTime = MutableStateFlow("")
    val inputTime: StateFlow<String> = _inputTime

    private val _inputNotes = MutableStateFlow("")
    val inputNotes: StateFlow<String> = _inputNotes


    private val _snackbarState = MutableStateFlow<SnackbarState>(SnackbarState())
    val snackbarState: StateFlow<SnackbarState> = _snackbarState

    fun onInputNameChange(newValue: String) {
        _inputName.value = newValue
    }

    fun onInputFormChange(newValue: String) {
        _inputForm.value = newValue
    }

    fun onInputStrengthChange(newValue: String) {
        _inputStrength.value = newValue
    }

    fun onInputUnitChange(newValue: String) {
        _inputUnit.value = newValue
    }

    fun onInputFrequencyChange(newValue: String) {
        _inputFrequency.value = newValue
    }

    fun onInputTimeChange(newValue: String) {
        _inputTime.value = newValue
    }

    fun onInputNotesChange(newValue: String) {
        _inputNotes.value = newValue
    }

    fun showSnackbar(message: String) {
        viewModelScope.launch {
            _snackbarState.value = SnackbarState(message)
        }
    }

    fun addMedication(newMed: UserMedication) {
        db.collection("medication")
            .add(newMed)
            .addOnSuccessListener {
                showSnackbar("Medication added!")
                Log.d(TAG, "DocumentSnapshot added with ID: ${it.id}")
            }
            .addOnFailureListener {
                showSnackbar("Error!")
                Log.d(TAG, "Error!")
            }
    }
}

data class SnackbarState(val message: String? = null)