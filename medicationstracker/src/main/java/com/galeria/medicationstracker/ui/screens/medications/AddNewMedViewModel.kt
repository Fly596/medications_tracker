package com.galeria.medicationstracker.ui.screens.medications

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.galeria.medicationstracker.data.MedicationForms
import com.galeria.medicationstracker.data.MedicationUnit
import com.galeria.medicationstracker.data.UserMedication
import com.galeria.medicationstracker.model.FirestoreFunctions.FirestoreService
import com.google.firebase.Timestamp
import com.google.firebase.appcheck.internal.util.Logger.TAG
import com.google.firebase.auth.FirebaseAuth

data class NewMedicationUiState(
    val uid: String = "",
    val medName: String = "",
    var medForm: MedicationForms = MedicationForms.TABLET, // f
    val medStrength: Float = 0.0f,
    val medUnit: MedicationUnit = MedicationUnit.MG, // f
    val medStartDate: Timestamp = Timestamp.now(), // f
    val medEndDate: Timestamp = Timestamp.now(), // f
    val medIntakeTime: String = "", // f
    val medNotes: String = "",
    val showDatePicker: Boolean = false,
    val showTimePicker: Boolean = false,
    val intakeDays: List<String> = emptyList(),
)

class AddNewMedViewModel : ViewModel() {

    var uiState by mutableStateOf(NewMedicationUiState())
        private set
    val db = FirestoreService.db
    val auth = FirebaseAuth.getInstance()
    val userId = auth.currentUser?.uid
    val userLogin = auth.currentUser?.email

    // Добавление нового лекарства в Firestore.
    fun addMedication(
        context: Context,
    ) {
        // Проверка на пустые значения текстовых полей и нулевое значение medStrength
        if (uiState.medName.isBlank() ||
            uiState.medForm.toString().isBlank() ||
            uiState.medUnit.toString().isBlank() ||
            uiState.medStrength <= 0 ||
            uiState.medStartDate.toString().isBlank() ||
            uiState.medEndDate.toString().isBlank()
        ) {
            Toast.makeText(
                context,
                "Please fill in all required fields correctly!",
                Toast.LENGTH_SHORT
            ).show()
            Log.w(TAG, "Validation failed: Missing or incorrect input fields.")
            return
        }
        val medicationRef = db
            .collection("UserMedication")
        val documentId = "${userLogin}_${uiState.medName}_${uiState.medStrength}"
        // Проверка на дубликаты
        medicationRef.document(documentId).get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    // Документ уже существует
                    Toast.makeText(
                        context,
                        "Medication already exists!",
                        Toast.LENGTH_SHORT
                    ).show()

                    Log.d(TAG, "Medication already exists with ID: $documentId")
                } else {
                    // Документ не существует, добавляем новый
                    val newUserMedication = UserMedication(
                        userId,
                        uiState.medName,
                        uiState.medForm.toString(),
                        uiState.medStrength,
                        uiState.medUnit.toString(),
                        uiState.medStartDate,
                        uiState.medEndDate,
                        uiState.intakeDays,
                        uiState.medIntakeTime,
                        uiState.medNotes
                    )

                    medicationRef.document(documentId)
                        .set(newUserMedication)
                        .addOnSuccessListener {
                            Toast.makeText(
                                context,
                                "DocumentSnapshot added successfully!",
                                Toast.LENGTH_SHORT
                            ).show()

                            Log.d(TAG, "DocumentSnapshot added with ID: $documentId")
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(context, "Error adding medication", Toast.LENGTH_SHORT)
                                .show()
                            Log.w(TAG, "Error adding document", e)
                        }
                }
            }
    }

    fun updateStartDate(input: Timestamp?) {
        uiState = uiState.copy(medStartDate = input ?: Timestamp.now())
    }

    fun updateEndDate(input: Timestamp?) {
        uiState = uiState.copy(medEndDate = input ?: Timestamp.now())
    }

    fun updateMedName(newName: String) {
        uiState = uiState.copy(medName = newName)
    }

    fun updateMedForm(newForm: MedicationForms) {
        uiState = uiState.copy(medForm = newForm)
    }

    fun updateMedStrength(newStrength: Float) {
        uiState = uiState.copy(medStrength = newStrength/* .toFloat() */)
    }

    fun updateMedUnit(newUnit: MedicationUnit) {
        uiState = uiState.copy(medUnit = newUnit)
    }

    fun updateIntakeTime(newTime: String) {
        uiState = uiState.copy(medIntakeTime = newTime)
    }

    fun updateMedNotes(newNotes: String) {
        uiState = uiState.copy(medNotes = newNotes)
    }

    fun isShowDateChecked(input: Boolean) {
        uiState = uiState.copy(showDatePicker = !input)
    }

    fun isShowTimeChecked(input: Boolean) {
        uiState = uiState.copy(showTimePicker = !input)
    }

    fun updateSelectedDays(input: List<String>) {
        uiState = uiState.copy(intakeDays = uiState.intakeDays + input)
    }

}
