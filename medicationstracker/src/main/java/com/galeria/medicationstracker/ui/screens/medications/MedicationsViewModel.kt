package com.galeria.medicationstracker.ui.screens.medications

import androidx.lifecycle.*
import com.galeria.medicationstracker.data.*
import com.google.firebase.*
import com.google.firebase.auth.*
import com.google.firebase.firestore.*
import kotlinx.coroutines.flow.*

data class UserId(
  val patient: String = "KT95DFgGbgYt90QtKjIYSApXKqw1",
  val doctor: String = "suAPx8M00vdYqqpWnahF7Ce6pJl2",
  val admin: String = "sT3E84Rw8oNiI4hBQwavVo3dhjy1",
  
  )

class MedicationsViewModel : ViewModel() {
  
  val ids = listOf(
    "KT95DFgGbgYt90QtKjIYSApXKqw1",
    "suAPx8M00vdYqqpWnahF7Ce6pJl2",
    "sT3E84Rw8oNiI4hBQwavVo3dhjy1",
  )
  
  // Initialize Firebase Auth and Firestore.
  private val user = Firebase.auth.currentUser
  private val db = Firebase.firestore
  
  // val userId = user?.uid ?: ""
  val userId = ids[0] //
  
  // Initialize data.
  private var _userMedications = MutableStateFlow<List<UserMedication>>(emptyList())
  var userMedications = _userMedications.asStateFlow()
  
  init {
    getUserMedicationsFromFirestore()
  }
  
  private fun getUserMedicationsFromFirestore() {
    
    db.collection("UserMedication")
      .whereEqualTo("uid", userId)
      .addSnapshotListener { value, error ->
        if (error != null) {
          return@addSnapshotListener
        }
        
        if (value != null) {
          _userMedications.value = value.toObjects()
        }
        
      }
  }
  
  fun deleteMedicationFromFirestore(medName: String) {
    val db = FirebaseFirestore.getInstance()
    
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
  
  fun updateMedicationInFirestore(medName: String) {
    val db = FirebaseFirestore.getInstance()
    
    // db.collection("UserMedication")
    
  }
}

/*
data class MedicationsUiState(
  val uid: String = "",
  val medName: String = "",
  val medForm: MedicationForms = MedicationForms.TABLET,
  val medStrength: Float = 1.0f,
  val medUnit: MedicationUnit = MedicationUnit.MG,
  val medStartDate: String = "",
  val medEndDate: String = "",
  val medIntakeTime: String = "",
  val medNotes: String = "",
)

class MedicationsViewModel : ViewModel() {

  // Initialize Firebase Auth and Firestore.
  private val db = Firebase.firestore
  private val user = Firebase.auth.currentUser

  // Initialize UI state.
  private val _uiState = MutableStateFlow(MedicationsUiState())
  val uiState = _uiState

  private var _userMedications =
    MutableStateFlow<List<UserMedication>>(emptyList())
  val userMmedication = _userMedications.asStateFlow()

  var showDatePicker by mutableStateOf(false)

  init {
    getAllusersMedsList()
  }

  fun getAllusersMedsList() {
    db.collection("UserMedication")
      .addSnapshotListener { value, error ->
        if (error != null) {
          return@addSnapshotListener
        }

        if (value != null) {
          _userMedications.value = value.toObjects()
        }

      }
  }

   */
/**
 * Retrieves the list of medications associated with the current user from Firestore.
 *
 * This function queries the "user_medications" collection in Firestore, filtering by the
 * current user's UID. It sets the `_userMedications` LiveData with the retrieved list
 * of medications.
 *
 * In case of an error during the retrieval process, it logs the error to the console.
 *//*
  fun getMedsList() {
    val userId = user?.uid ?: ""

    viewModelScope.launch {
      try {
        db.collection("UserMedication")
          .whereEqualTo("uid", userId)
          .addSnapshotListener { snapshot, error ->
            if (error != null) {
              return@addSnapshotListener
            }
            if (snapshot != null) {
              _userMedications.value = snapshot.toObjects()
            }
          }
      } catch (e: Exception) {
        Log.d(TAG, "Error getting documents: ", e)
      }
    }
  }


} */

/*  fun getMedsList() {

    // TODO: get meds from firebase.
    db.collection("med_temp").addSnapshotListener { value, error ->
      if (error != null) {
        return@addSnapshotListener
      }

      if (value != null) {
        _userMedications.value = value.toObjects()
      }
    }*/
