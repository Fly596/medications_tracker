package com.example.speechrecognitionapp.ui.screens.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.speechrecognitionapp.data.AppUser
import com.google.firebase.Firebase
import com.google.firebase.appcheck.internal.util.Logger.TAG
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileScreenViewModel : ViewModel() {

    private var _user = MutableStateFlow<AppUser?>(null)
    val user = _user.asStateFlow()

    private var db = Firebase.firestore

    fun addUser(
        born: Int,
        email: String,
        first: String,
        last: String,
        password: String
    ) {
        val appUser = hashMapOf(
            "born" to born,
            "email" to email,
            "first" to first,
            "last" to last,
            "password" to password
        )

        db.collection("users")
            .add(appUser)
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot added with ID: ${it.id}")
            }
    }

    fun updateUser(updatedUser: AppUser) {
        db.collection("users")
            .document("icBRUFO4CCubhIjVegfK")
            .set(updatedUser)
            .addOnSuccessListener {
                Log.d(TAG, "User updated")
            }
    }
}