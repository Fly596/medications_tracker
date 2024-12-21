package com.galeria.medicationstracker.ui.screens.dashboard

import android.util.*
import androidx.lifecycle.*
import com.galeria.medicationstracker.data.*
import com.galeria.medicationstracker.model.*
import com.galeria.medicationstracker.model.FirestoreFunctions.*
import com.google.firebase.*
import com.google.firebase.auth.*
import dagger.hilt.android.lifecycle.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.*
import javax.inject.*

@HiltViewModel
class DashboardVM @Inject constructor() : ViewModel() {

  val currentUserId = FirebaseAuth.getInstance().currentUser?.uid
  private var _currentTakenMedications =
    MutableStateFlow<List<UserMedication>>(emptyList())
  var currentTakenMedications = _currentTakenMedications.asStateFlow()

  init {
    // Получение списка активных лекарств пациента.
    getCurrentMedications()
  }

  var showToastCallback: ((String) -> Unit)? = null

  // Фильтрация лекарств, прием которых окончен для использования при выводе на главный экран.
  fun getCurrentMedications() {
    val todayWeekDay = formatStringDateToWeekday(Timestamp.now()).uppercase()

    FirestoreService.db.collection("UserMedication")
        .whereEqualTo("uid", currentUserId)
        .whereGreaterThanOrEqualTo("endDate", Timestamp.now())
        .whereArrayContains("frequency", todayWeekDay.toString())
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
    // Проверка на дублирование приема.
    // var cre = checkIntake(medication)

    // текущее время до дней.
    var dateday = formatTimestampTillTheDay(intakeTime)
    var datesec = formatTimestampTillTheSec(intakeTime)
    println("Day: $dateday")
    println("Sec: $datesec")
    // println("Is exist?: $cre")

    val intake = UserIntake(
      uid = currentUserId.toString(),
      medicationName = medication.name.toString(),
      dose = medication.strength.toString(),
      status = status,
      dateTime = Timestamp.now()
    )

    FirestoreService.db.collection("MedicationIntake")
        .document("${FirebaseAuth.getInstance().currentUser?.email}_${medication.name}_${dateday}")
        .set(intake)

  }

  // Проверка на то, был ли сегодня прием или нет.
  suspend fun checkIntake(medication: UserMedication): Boolean {
    var todaysDate = Timestamp.now()
    var ret = false

    var takerMeds = MutableStateFlow<List<UserIntake>>(emptyList())
    try {
      val querySnapshot = FirestoreService.db.collection("MedicationIntake")
          .whereEqualTo("uid", currentUserId)
          .whereEqualTo("medicationName", medication.name)
          .orderBy("dateTime")
          .limit(1)
          .get()
          .await()

      if (!querySnapshot.isEmpty) {
        ret = true
      } else {
        ret = false
      }
      // ret = querySnapshot.isEmpty
    } catch (e: Exception) {
      Log.e("DashboardVM", "Error fetching current medications: ${e.message}", e)
      println("Error fetching current medications: ${e.message}")
      ret = false
    }
    return ret
    /*     FirestoreService.db.collection("MedicationIntake")
            .whereEqualTo("uid", currentUserId)
            .whereEqualTo("medicationName", medication.name)
            .addSnapshotListener { medicationSnapshots, error ->
              if (error != null) {
                Log.e(
                  "DashboardVM",
                  "Error fetching current medications: ${error.message}",
                  error
                )
                println("Error fetching current medications: ${error.message}")
                return@addSnapshotListener
              }

              medicationSnapshots?.let {
                takerMeds.value = it.toObjects(UserMedication::class.java) as List<UserIntake>
                println("Medications loaded successfully")
                showToastCallback?.invoke("Medications loaded successfully")

              }
            }

        FirestoreService.db.collection("MedicationIntake")
            .whereEqualTo("uid", currentUserId)
            .whereEqualTo("medicationName", medication.name)
            .whereLessThanOrEqualTo("dateTime", todaysDate)
            .get()
            .addOnSuccessListener {
              if (it.isEmpty) {
                // Если такого приема нема, то окэ.
                ret = true
              } else if (!it.isEmpty) {
                ret = false
              }
            }
            .addOnFailureListener {
              ret = false
            }
        return ret */
  }

  /*
  // Проверка на то, был ли сегодня прием или нет
    fun hasIntakeToday(medicationName: String) {
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