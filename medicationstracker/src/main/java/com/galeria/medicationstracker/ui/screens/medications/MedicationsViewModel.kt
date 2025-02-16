package com.galeria.medicationstracker.ui.screens.medications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galeria.medicationstracker.data.MedicationsRepository
import com.galeria.medicationstracker.data.UserMedication
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MedicationsUiState(
    val userMedications: List<UserMedication> = emptyList(),
)

@HiltViewModel
class MedicationsViewModel @Inject constructor(
    private val repository: MedicationsRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(MedicationsUiState())
    val uiState = _uiState.asStateFlow()
    private val userId = FirebaseAuth.getInstance().currentUser?.uid
    
    init {
        // Получение всех пользовательских лекарств.
        viewModelScope.launch {
            // TODO: change to flowdata
            val medications = repository.getDrugs(userId.toString())
            _uiState.value = _uiState.value.copy(userMedications = medications)
        }
        // fetchUserMedications()
    }
    
    // Удаление лекарства из Firestore.
    fun deleteMedicationFromFirestore(medName: String) {
        viewModelScope.launch {
            repository.deleteDrug(medName)
        }
    }
}
/*
class MedicationsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MedicationsUiState())
    val uiState = _uiState.asStateFlow()
    private val db = FirestoreService.db
    val firebaseAuth = FirebaseAuth.getInstance()
    private val user = firebaseAuth.currentUser
    val userId = user?.uid
    private var _userMedications = MutableStateFlow<List<UserMedication>?>(null)

    init {
        fetchUserMedications()
    }

    // Получение всех пользовательских лекарств.
    private fun fetchUserMedications() {
        val docRef = db.collection("UserMedication")
        val source = Source.CACHE

        docRef
            .whereEqualTo("uid", userId)
            .get(source)
            .addOnSuccessListener { result ->
                val medications = result.toObjects(UserMedication::class.java)
                _uiState.value = _uiState.value.copy(userMedications = medications)
            }
            .addOnFailureListener { ex ->
                println("Error finding documents: $ex")
            }
    }

    // Удаление лекарства из Firestore.
    fun deleteMedicationFromFirestore(medName: String) {
        db.collection("UserMedication")
            .whereEqualTo("name", medName)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    for (document in querySnapshot.documents) {
                        db.collection("UserMedication")
                            .document(document.id)
                            .delete()
                            .addOnSuccessListener {
                                println("Document with ID ${document.id} successfully deleted!")
                            }
                            .addOnFailureListener { e ->
                                println("Error deleting document with ID ${document.id}: $e")
                            }
                    }
                } else {
                    println("No document found with the name: ${medName}")
                }
            }
            .addOnFailureListener { e ->
                println("Error finding documents to delete: $e")
            }
    }
}
*/


