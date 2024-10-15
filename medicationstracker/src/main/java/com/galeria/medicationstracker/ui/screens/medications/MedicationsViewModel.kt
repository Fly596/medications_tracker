package com.galeria.medicationstracker.ui.screens.medications

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galeria.medicationstracker.data.Frequency
import com.galeria.medicationstracker.data.Medication
import com.galeria.medicationstracker.data.MedicationForm
import com.galeria.medicationstracker.data.MedicationUnit
import com.galeria.medicationstracker.data.TEMP_Medication
import com.google.firebase.Firebase
import com.google.firebase.appcheck.internal.util.Logger.TAG
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

data class MedicationInputState(
  val uid: String = "",
  val name: String = "",
  val type: String = "",
  val form: MedicationForm = MedicationForm.TABLET, // f
  val strength: Float = 0.0f,
  val unit: MedicationUnit = MedicationUnit.MG, // f
  val startDate: LocalDate = LocalDate.now(),// f
  val endDate: LocalDate? = null,// f
  val frequency: Frequency = Frequency.AtRegularIntervals(0),// f
  val intakeTime: LocalTime = LocalTime.now(),// f
  val notes: String = "",
)

class MedicationsViewModel : ViewModel() {

  private val _medicationState = MutableStateFlow(MedicationInputState())
  val medicationState: StateFlow<MedicationInputState> = _medicationState.asStateFlow()


  private val _userMedications =
    MutableStateFlow<List<TEMP_Medication>>(emptyList())
  val userMedications = _userMedications.asStateFlow()

  private val db = Firebase.firestore
  private val user = Firebase.auth.currentUser
  val userId = user?.uid ?: ""

  init {
    getMedsList()
  }

  fun getMedsList() {
    viewModelScope.launch {
      try {
        db.collection("med_temp")
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

  fun updateMedicationField(field: (MedicationInputState) -> MedicationInputState) {
    _medicationState.update { currentMedication ->
      field(currentMedication)
    }
  }


  fun updateMedName(newName: String) {
    updateMedicationField {
      it.copy(name = newName)
    }
  }

  fun updateMedType(newType: String) {
    updateMedicationField { it.copy(type = newType) }
  }

  fun updateMedForm(newForm: MedicationForm) {
    updateMedicationField { it.copy(form = newForm) }
  }

  fun updateMedStrength(newStrength: Float) {
    updateMedicationField { it.copy(strength = newStrength) }
  }

  fun updateMedUnit(newUnit: MedicationUnit) {
    updateMedicationField { it.copy(unit = newUnit) }
  }

  fun updateStartDate(newStartDate: LocalDate) {
    updateMedicationField { it.copy(startDate = newStartDate) }
  }

  fun updateEndDate(newEndDate: LocalDate) {
    updateMedicationField { it.copy(endDate = newEndDate) }
  }

  fun updateMedFrequency(newFrequency: Frequency) {
    updateMedicationField { it.copy(frequency = newFrequency) }
  }

  fun updateIntakeTime(newTime: LocalTime) {
    updateMedicationField { it.copy(intakeTime = newTime) }
  }

  fun updateMedNotes(newNotes: String) {
    updateMedicationField { it.copy(notes = newNotes) }
  }

  /*     private val _medType = MutableStateFlow("")
      val medType = _medType.asStateFlow()
      private val _medForm = MutableStateFlow(MedicationForm.TABLET)
      val medForm = _medForm.asStateFlow()
      private val _medStrength = MutableStateFlow(0.0f)
      val medStrength = _medStrength.asStateFlow()
      private val _medUnit = MutableStateFlow(MedicationUnit.MG)
      val medUnit = _medUnit.asStateFlow()


      fun updateMedStrength(newStrength: Float) {
        _medStrength.value = newStrength
      }


      fun updateMedUnit(newUnit: MedicationUnit) {
        _medUnit.value = newUnit
      }

      private val _startDate = MutableStateFlow(LocalDate.now())
      val startDate = _startDate.asStateFlow()
      fun updateStartDate(newStartDate: LocalDate) {
        _startDate.value = newStartDate
      }

      private val _endDate = MutableStateFlow(LocalDate.now())
      val endDate = _endDate.asStateFlow()
      fun updateEndDate(newEndDate: LocalDate) {
        _endDate.value = newEndDate
      }

       *//*   private val _medFrequency = MutableStateFlow(Frequency.AtRegularIntervals(0))
      val medFrequency = _medFrequency.asStateFlow()
      fun updateMedFrequency(newFrequency: Frequency) {
        _medFrequency.value = newFrequency
      } *//*

    private val _intakeTime = MutableStateFlow(LocalTime.now())
    val intakeTime = _intakeTime.asStateFlow()
    fun updateIntakeTime(newTime: LocalTime) {
      _intakeTime.value = newTime
    }

    private val _medNotes = MutableStateFlow("")
    val medNotes = _medNotes.asStateFlow()
    fun updateMedNotes(newNotes: String) {
      _medNotes.value = newNotes
    } */


  // TODO: get meds from firebase.

  /*         .get()
    .addOnSuccessListener { docs ->
      for (doc in docs) {

        doc.data["name"]
        doc.data["type"]
        doc.data["uid"]

        val medication = doc.toObject<Medication>()
        _userMedications.value += medication

        // Log.d(TAG, "${doc.id} => ${doc.data}")
        Log.d(TAG, "${doc.id} => ${medication.name}, ${medication.form}, ${medication.uid}")
        Log.d(TAG, "${doc.id} => ${doc.data["name"]}, ${doc.data["form"]}, ${doc.data["uid"]}")
      }
    }

    .addOnFailureListener { exception ->
      Log.w(TAG, "Error getting documents.", exception)
    } */


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
