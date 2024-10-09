package com.galeria.medicationstracker.ui.screens.medications

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.galeria.medicationstracker.data.TEMP_Medication
import com.google.firebase.Firebase
import android.content.Context
import androidx.lifecycle.viewModelScope

import com.google.firebase.appcheck.internal.util.Logger.TAG
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MedicationsViewModel : ViewModel() {

  private var db = Firebase.firestore
  private val user = Firebase.auth.currentUser
  val userId = user?.uid ?: ""

  private val _medications = MutableStateFlow<List<TEMP_Medication>>(emptyList())
  val medications = _medications.asStateFlow()

  private val _medName = MutableStateFlow("")
  val medName = _medName.asStateFlow()

  private val _medType = MutableStateFlow("")
  val medType = _medType.asStateFlow()

  fun updateMedName(newName: String) {
    _medName.value = newName
  }

  fun updateMedType(newType: String) {
    _medType.value = newType
  }


  init {
    fetchMedications()
  }

 fun fetchMedications() {
   db.collection("med_temp")
     .get()
     .addOnSuccessListener { docs->
       for (doc in docs) {
         val medication = doc.toObject<TEMP_Medication>()
         _medications.value += medication

         Log.d(TAG, "meds: ${medication.name}")
       }
     }
     .addOnFailureListener{exception->
       Log.w(TAG, "Error getting documents.", exception)

     }
/*    viewModelScope.launch {
      try {
        val querySnapshot = db.collection("med_temp").get()
          .addOnSuccessListener { docs ->
            for (doc in docs) {
              Log.d(TAG, "${doc.id} => ${doc.data}")
            }
          }
          .addOnFailureListener { exception ->
            Log.w(TAG, "Error getting documents.", exception)
          }
      } catch (e: Exception) {
        Log.d(TAG, "Error getting documents: ", e)

      }
    }*/
  }

  /*private val _userMedications =
    MutableStateFlow<List<TEMP_Medication>>(emptyList())
  val userMedications*//*: StateFlow<List<TEMP_Medication>> get()*//* = _userMedications.asStateFlow()
*/


  private fun getMedsList() {
    viewModelScope.launch {
      try {
        db.collection("med_temp").addSnapshotListener{
          value, error ->
          if (error != null) {
            return@addSnapshotListener
          }
          if (value != null) {
            _medications.value = value.toObjects()
          }
        }


        /*val medList = res.documents.map { document ->
          document.toObject(TEMP_Medication::class.java) ?: TEMP_Medication()
          Log.d(TAG, "${document.id} => ${document.data}")
        }*/

        //_userMedications.value = doc
      } catch (e: Exception) {
        Log.d(TAG, "Error getting documents: ", e)
      }
    }
    /*    db.collection("med_temp").whereEqualTo()
          .get()
          .addOnSuccessListener { docs ->
            for (doc in docs) {
              Log.d(TAG, "${doc.id} => ${doc.data}")
            }
          }
          .addOnFailureListener { exception ->
            Log.w(TAG, "Error getting documents.", exception)
          }*/
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

  fun addMedication(
    medication: TEMP_Medication,
    context: Context,
  ) {
    db.collection("med_temp").add(medication)
      .addOnSuccessListener {
        Toast.makeText(
          context,
          "DocumentSnapshot added successfully!",
          Toast.LENGTH_SHORT
        ).show()

        Log.d(TAG, "DocumentSnapshot added with ID: ${it.id}")
      }
      .addOnFailureListener { e ->
        Log.w(TAG, "Error adding document", e)
      }
  }
}
