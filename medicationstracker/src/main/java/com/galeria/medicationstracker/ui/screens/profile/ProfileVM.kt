package com.galeria.medicationstracker.ui.screens.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.galeria.medicationstracker.ui.screens.medications.update.UpdateMedUiState
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

data class ProfileScreenUiState(
    val testData: String = ""
)

class ProfileVM : ViewModel() {

    var uiState by mutableStateOf(ProfileScreenUiState())
        private set


    private lateinit var auth: FirebaseAuth

    private var db = Firebase.firestore

    // todo
    fun onLogoutClick() {
        auth = FirebaseAuth.getInstance()

        // auth.signOut()
    }
}
