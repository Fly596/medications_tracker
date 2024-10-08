package com.galeria.medicationstracker.ui.screens.medications

import android.util.Log
import androidx.lifecycle.ViewModel
import com.galeria.medicationstracker.data.TEMP_Medication
import com.galeria.medicationstracker.data.Medication
import com.google.firebase.Firebase
import com.google.firebase.appcheck.internal.util.Logger.TAG
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MedicationsViewModel : ViewModel() {

    private val _userMedications = MutableStateFlow<List<TEMP_Medication>>(emptyList())
    val userMedications = _userMedications.asStateFlow()

    private var db = Firebase.firestore

    fun getMedsList() {
        // TODO: get meds from firebase.
        db.collection("med_temp").addSnapshotListener { value, error ->
            if (error != null) {
                return@addSnapshotListener
            }

            if (value != null) {
                _userMedications.value = value.toObjects()
            }
        }
    }

    fun addMedication(medication: Medication) {
        db.collection("medication").add(medication).addOnSuccessListener {
            Log.d(TAG, "DocumentSnapshot added with ID: ${it.id}")
        }
    }
}
