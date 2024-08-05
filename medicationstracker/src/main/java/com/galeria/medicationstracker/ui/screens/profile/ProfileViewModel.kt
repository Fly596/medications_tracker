package com.galeria.medicationstracker.ui.screens.profile

import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class ProfileViewModel: ViewModel() {

    private lateinit var auth: FirebaseAuth

    private var db = Firebase.firestore

    // todo
    fun onLogoutClick() {
        auth = FirebaseAuth.getInstance()

        //auth.signOut()
    }

}