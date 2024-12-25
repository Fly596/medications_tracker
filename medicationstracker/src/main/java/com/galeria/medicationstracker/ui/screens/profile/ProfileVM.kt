package com.galeria.medicationstracker.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galeria.medicationstracker.data.User
import com.galeria.medicationstracker.model.FirestoreFunctions.FirestoreService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Source
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ProfileScreenUiState(
    val testData: String = "",
    val user: User? = null,
)

class ProfileVM : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileScreenUiState())
    val uiState: StateFlow<ProfileScreenUiState> = _uiState

    val db = FirestoreService.db
    val firebaseAuth = FirebaseAuth.getInstance()
    val currentUser = firebaseAuth.currentUser
    val currentUserId = firebaseAuth.currentUser?.uid

    init {
        fetchUserData()
    }

    private fun fetchUserData() {
        viewModelScope.launch {
            val userRef = db.collection("User")
            val source = Source.CACHE

            userRef.whereEqualTo("uid", currentUserId)
                .get(source)
                .addOnSuccessListener { result ->
                    val user = result.toObjects(User::class.java).firstOrNull()
                    _uiState.value = _uiState.value.copy(user = user)
                }
                .addOnFailureListener { exp ->
                    println("Error fetching user data: ${exp.message}")
                }
        }

    }
}
