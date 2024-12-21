package com.galeria.medicationstracker.ui.doctor.patients

import androidx.lifecycle.ViewModel
import com.galeria.medicationstracker.data.User
import com.galeria.medicationstracker.model.FirestoreFunctions.FirestoreService
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PatientsListViewModel : ViewModel() {

  private val db = FirestoreService.db

  private var _patients = MutableStateFlow<List<User>>(emptyList())
  var patients = _patients.asStateFlow()

  init {
    fetchPatients()
  }

  private fun fetchPatients() {
    val docRef = db.collection("users")

    docRef
        .whereEqualTo("type", "PATIENT")
        .get()
        .addOnSuccessListener { result ->
          _patients.value = result.toObjects()
        }
        .addOnFailureListener { exception ->
          println("Error finding documents: $exception")
        }
  }
}