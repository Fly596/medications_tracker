package com.galeria.medicationstracker.ui.screens.dashboard

import android.util.*
import androidx.lifecycle.*
import com.galeria.medicationstracker.data.*
import com.galeria.medicationstracker.model.FirestoreFunctions.*
import com.google.firebase.*
import com.google.firebase.auth.*
import dagger.hilt.android.lifecycle.*
import kotlinx.coroutines.flow.*
import javax.inject.*

@HiltViewModel
class DashboardVM @Inject constructor() : ViewModel() {

  val currentUserId = FirebaseAuth.getInstance().currentUser?.uid
  private var _currentTakenMedications =
    MutableStateFlow<List<UserMedication>>(emptyList())
  var currentTakenMedications = _currentTakenMedications.asStateFlow()

  init {
    getCurrentMedications()
  }

  // Получение списка лекарств пациента.

  var showToastCallback: ((String) -> Unit)? = null

  // Фильтрация лекарств, прием которых окончен для использования при выводе на главный экран.
  fun getCurrentMedications() {

    FirestoreService.db.collection("UserMedication")
        .whereEqualTo("uid", currentUserId)
        .whereGreaterThanOrEqualTo("endDate", Timestamp.now())
        .whereArrayContains("frequency", "WEDNESDAY")
        .addSnapshotListener { medicationSnapshots, error ->
          if (error != null) {
            Log.e(
              "DashboardVM",
              "Error fetching current medications: ${error.message}",
              error
            )
            showToastCallback?.invoke("Error loading medications")
            return@addSnapshotListener
          }

          medicationSnapshots?.let {
            _currentTakenMedications.value =
              it.toObjects(UserMedication::class.java)
            showToastCallback?.invoke("Medications loaded successfully")
          }
        }
  }

  // Запись приема в бд.
  fun recordMedicationIntake(
      intakeTime: Timestamp = Timestamp.now(),
      medication: UserMedication = UserMedication(),
      status: Boolean = true
  ) {

    val intake = UserIntake(
      uid = currentUserId.toString(),
      medicationName = medication.name.toString(),
      dose = "1",
      status = status,
      dateTime = Timestamp.now()
    )

    FirestoreService.db.collection("MedicationIntake").document("${medication.name}${intakeTime}")
        .set(intake)
  }

  // Проверка на то, был ли сегодня прием или нет
  /*   fun hasIntakeToday(medicationName: String) {
      val temp = LocalDateTime.now().toTimestamp()
      val temp2 = Timestamp.now().toLocalDateTime()

      FirestoreService.db.collection("MedicationIntake")
          .whereEqualTo("uid", currentUserId)
          .whereEqualTo("medicationName", medicationName)
          .whereLessThanOrEqualTo("dateTime", LocalDateTime.now().toTimestamp())
          .get()
          .addOnSuccessListener {
            Log.d("DashboardVM", "hasIntakeToday: ${it.isEmpty}")
          }
          .addOnFailureListener {
            Log.d("DashboardVM", "hasIntakeToday: ${it.message}")
          }

    } */
}