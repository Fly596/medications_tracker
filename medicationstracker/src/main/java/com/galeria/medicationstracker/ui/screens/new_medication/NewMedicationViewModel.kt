package com.galeria.medicationstracker.ui.screens.new_medication

import android.util.Log
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

class NewMedicationViewModel: ViewModel() {
    private val db = Firebase.firestore

    private val _snackbarState = MutableStateFlow<SnackbarState>(SnackbarState())
    val snackbarState: StateFlow<SnackbarState> = _snackbarState

    fun showSnackbar(message: String) {
        viewModelScope.launch {
            _snackbarState.value = SnackbarState(message)
        }
    }

    fun addMedication(newMed: UserMedication){
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