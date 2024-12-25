package com.galeria.medicationstracker.ui.screens.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galeria.medicationstracker.data.UserIntake
import com.galeria.medicationstracker.data.UserMedication
import com.galeria.medicationstracker.model.FirestoreFunctions.FirestoreService
import com.galeria.medicationstracker.model.formatStringDateToWeekday
import com.galeria.medicationstracker.model.formatTimestampTillTheDay
import com.galeria.medicationstracker.model.formatTimestampTillTheSec
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Source
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class DashboardVM() : ViewModel() {

    // Лекарства, которые нужно принимать.
    private var _currentTakenMedications = MutableStateFlow<List<UserMedication>>(emptyList())
    var currentTakenMedications = _currentTakenMedications.asStateFlow()

    val db = FirestoreService.db
    val firebaseAuth = FirebaseAuth.getInstance()
    val currentUserId = firebaseAuth.currentUser?.uid

    init {
        // Получение списка активных лекарств пациента.
        getCurrentMedications()
    }

    var showToastCallback: ((String) -> Unit)? = null

    // Фильтрация лекарств, прием которых окончен для использования при выводе на главный экран.
    fun getCurrentMedications() {
        val todayWeekDay = formatStringDateToWeekday(Timestamp.now()).uppercase()

        viewModelScope.launch {
            FirestoreService.db.collection("UserMedication")
                .whereEqualTo("uid", currentUserId)
                .whereGreaterThanOrEqualTo("endDate", Timestamp.now())
                .whereArrayContains("daysOfWeek", todayWeekDay.toString())
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
        val source = Source.CACHE

        var takerMeds = MutableStateFlow<List<UserIntake>>(emptyList())
        try {
            val querySnapshot = FirestoreService.db.collection("MedicationIntake")
                .whereEqualTo("uid", currentUserId)
                .whereEqualTo("medicationName", medication.name)
                .limit(1)
                .get(source)
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

    }

}