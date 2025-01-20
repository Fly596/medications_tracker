package com.galeria.medicationstracker.ui.screens.dashboard.record

import androidx.lifecycle.*
import com.galeria.medicationstracker.data.*
import com.galeria.medicationstracker.utils.FirestoreFunctions.FirestoreService
import com.google.firebase.auth.*
import com.google.firebase.firestore.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

data class RecordsUiState(
    val intakes: List<UserIntake> = emptyList()
)

class RecordsVM : ViewModel() {
    
    private var _uiState = MutableStateFlow(RecordsUiState())
    val uiState = _uiState.asStateFlow()
    
    private val _intakesData = MutableStateFlow<List<UserIntake>>(emptyList())
    val intakesData: StateFlow<List<UserIntake>> = _intakesData.asStateFlow()
    val db = FirestoreService.db
    val firebaseAuth = FirebaseAuth.getInstance()
    val currentUserId = firebaseAuth.currentUser?.uid
    
    init {
        fetchUserIntakes()
    }
    
    private fun fetchUserIntakes() {
        viewModelScope.launch {
            val intakesRef = db.collection("User")
                .document("${FirebaseAuth.getInstance().currentUser?.email}")
                .collection("intakes")
                .orderBy("dateTime", Query.Direction.DESCENDING)
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
