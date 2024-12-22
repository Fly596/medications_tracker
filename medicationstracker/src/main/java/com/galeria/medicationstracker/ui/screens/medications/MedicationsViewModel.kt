package com.galeria.medicationstracker.ui.screens.medications

import androidx.lifecycle.ViewModel
import com.galeria.medicationstracker.data.UserMedication
import com.galeria.medicationstracker.model.FirestoreFunctions.FirestoreService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MedicationsViewModel : ViewModel() {

  var patient: String = "KT95DFgGbgYt90QtKjIYSApXKqw1"
  var doctor: String = "suAPx8M00vdYqqpWnahF7Ce6pJl2"
  var admin: String = "sT3E84Rw8oNiI4hBQwavVo3dhjy1"

  private val db = FirestoreService.db
  val firebaseAuth = FirebaseAuth.getInstance()
  private val user = firebaseAuth.currentUser

  val userId = user?.uid

  private var _userMedications = MutableStateFlow<List<UserMedication>>(emptyList())
  var userMedications = _userMedications.asStateFlow()

  init {
    // fetchUserMedications()
  }

  // Получение всех пользовательских лекарств.
  fun fetchUserMedications() {
    val docRef = db.collection("UserMedication")

    docRef
        .whereEqualTo("uid", userId)
        .get()
        .addOnSuccessListener { result ->
          _userMedications.value = result.toObjects()
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
