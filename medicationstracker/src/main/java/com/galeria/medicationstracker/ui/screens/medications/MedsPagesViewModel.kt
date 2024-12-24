package com.galeria.medicationstracker.ui.screens.medications

import android.util.Log
import androidx.lifecycle.ViewModel
import com.galeria.medicationstracker.data.UserMedication
import com.galeria.medicationstracker.model.FirestoreFunctions.FirestoreService
import com.google.firebase.appcheck.internal.util.Logger.TAG
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class MedsPagesUiState(
    val medications: List<UserMedication> = emptyList(),
    val selectedMed: UserMedication? = null,
)

class MedsPagesViewModel : ViewModel() {

    private var _uiState = MutableStateFlow(MedsPagesUiState())
    val uiState: StateFlow<MedsPagesUiState> = _uiState.asStateFlow()

    private val db = FirestoreService.db
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val user = firebaseAuth.currentUser
    private val userId = user?.uid

    fun getSelectedMed(medName: String) {
        // путь к коллекции с лекарствами
        val docRef = db.collection("UserMedication")

        docRef.whereEqualTo("uid", userId).whereEqualTo("name", medName)
            .get().addOnSuccessListener { docSnapshot ->
                val medications = docSnapshot.toObjects(UserMedication::class.java)
                _uiState.value = _uiState.value.copy(selectedMed = medications[0])
                Log.d(TAG, "Success getting document: ")

            }.addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)

                println("Error getting documents: $exception")
            }

    }

    fun getAllUserMeds() {
        val docRef = db.collection("UserMedications")

        docRef.whereEqualTo("uid", userId).get()
            .addOnSuccessListener {
                val medications = it.toObjects(UserMedication::class.java)
                updateMedications(medications)
            }
    }

    private fun updateMedications(medications: List<UserMedication>) {
        _uiState.value = _uiState.value.copy(medications = medications)
    }
}