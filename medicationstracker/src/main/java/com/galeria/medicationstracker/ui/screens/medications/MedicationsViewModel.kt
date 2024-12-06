package com.galeria.medicationstracker.ui.screens.medications

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galeria.medicationstracker.data.Medication
import com.galeria.medicationstracker.data.MedicationForms
import com.galeria.medicationstracker.data.MedicationUnit
import com.google.firebase.Firebase
import com.google.firebase.appcheck.internal.util.Logger.TAG
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.launch

data class MedicationsUiState(
  val uid: String = "",
  val name: String = "",
  val type: String = "",
  val form: MedicationForms = MedicationForms.TABLET, // f
  val strength: Float = 0.0f,
  val unit: MedicationUnit = MedicationUnit.MG, // f
  val startDate: String = "",// f
  val endDate: String= "",// f
  //val frequency: Frequency = Frequency.AtRegularIntervals(0),// f
  val intakeTime: String= "",// f
  val notes: String = "",
)

class MedicationsViewModel : ViewModel() {

  var uiState by mutableStateOf(MedicationsUiState())
    private set

  var userMedications =
    mutableStateOf<List<Medication>>(emptyList())
    private set
  //val userMedications = _userMedications.asStateFlow()

  var showDatePicker by mutableStateOf(false)



  private val db = Firebase.firestore
  private val user = Firebase.auth.currentUser
  val userId = user?.uid ?: ""

  init {
    getMedsList()
  }


  fun getMedsList() {
    viewModelScope.launch {
      try {
        db.collection("user_medications")
          .whereEqualTo("uid", userId)
          .addSnapshotListener { snapshot, error ->
            if (error != null) {
              return@addSnapshotListener
            }
            if (snapshot != null) {
              userMedications.value = snapshot.toObjects()
            }
          }
      } catch (e: Exception) {
        Log.d(TAG, "Error getting documents: ", e)
      }
    }
  }





}


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
