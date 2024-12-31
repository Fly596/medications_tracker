package com.galeria.medicationstracker.ui.screens.dashboard.record

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galeria.medicationstracker.data.UserIntake
import com.galeria.medicationstracker.utils.FirestoreFunctions.FirestoreService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class RecordsUiState(
    val intakes: List<UserIntake> = emptyList()
)

class RecordsVM : ViewModel() {

    private val _intakesData = MutableStateFlow<List<UserIntake>>(emptyList())
    val intakesData: StateFlow<List<UserIntake>> = _intakesData.asStateFlow()
    val db = FirestoreService.db
    val firebaseAuth = FirebaseAuth.getInstance()
    val currentUserId = firebaseAuth.currentUser?.uid
    private val _uiState = MutableStateFlow(RecordsUiState())
    val uiState: StateFlow<RecordsUiState> = _uiState

    init {
        fetchUserIntakes()
    }

    private fun fetchUserIntakes() {
        viewModelScope.launch {
            val intakesRef = db.collection("MedicationIntake")
            // val source = Source.CACHE
            intakesRef.whereEqualTo("uid", currentUserId).orderBy("dateTime")
                .get()
                .addOnSuccessListener { result ->
                    val intakes = result.toObjects(UserIntake::class.java)

                    _uiState.value = _uiState.value.copy(intakes = intakes)
                }
                .addOnFailureListener { exp ->
                    println("Error fetching intakes: ${exp.message}")
                }
        }

    }

}
