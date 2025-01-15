package com.galeria.medicationstracker.ui.screens.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galeria.medicationstracker.data.UserIntake
import com.galeria.medicationstracker.data.UserMedication
import com.galeria.medicationstracker.utils.FirestoreFunctions.FirestoreService
import com.galeria.medicationstracker.utils.formatTimestampTillTheDay
import com.galeria.medicationstracker.utils.formatTimestampTillTheSec
import com.galeria.medicationstracker.utils.formatTimestampToWeekday
import com.galeria.medicationstracker.utils.toTimestamp
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Source
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

class DashboardVM() : ViewModel() {

    // Лекарства, которые нужно принимать.
    private val _currentTakenMedications = MutableStateFlow<List<UserMedication>>(emptyList())
    val currentTakenMedications = _currentTakenMedications.asStateFlow()
    val db = FirestoreService.db
    val firebaseAuth = FirebaseAuth.getInstance()
    val currentUserId = firebaseAuth.currentUser?.uid

    init {
        // Получение списка активных лекарств пациента.
        getCurrentMedications()
    }

    var showToastCallback: ((String) -> Unit)? = null

    fun localDateTimeToTimestamp(localDateTime: LocalDateTime): Timestamp {
        val secs = localDateTime.atZone(ZoneId.systemDefault()).toEpochSecond()
        val nanos = localDateTime.nano

        return Timestamp(secs, nanos)
    }

    // Фильтрация лекарств, прием которых окончен для использования при выводе на главный экран.
    fun getCurrentMedications() {
        val todayStart = LocalDate.now().atStartOfDay().toTimestamp()
        val todayEnd = LocalDate.now().plusDays(1).atStartOfDay().toTimestamp()
        val todayWeekDay = formatTimestampToWeekday(Timestamp.now()).uppercase()

        viewModelScope.launch {
            FirestoreService.db.collection("UserMedication")
                .whereEqualTo("uid", currentUserId)
                .whereGreaterThanOrEqualTo("endDate", todayEnd)
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
    // -1: error; 0: noData, 1: skipped, 2: taken
    suspend fun checkIntake(medication: UserMedication): Int {
        val todayStart = LocalDate.now().atStartOfDay().toTimestamp()
        val todayEnd = LocalDate.now().plusDays(1).atStartOfDay().toTimestamp()
        var ret = -1
        val source = Source.DEFAULT
        var takerMeds = MutableStateFlow<List<UserIntake>>(emptyList())

        try {
            val querySnapshot = FirestoreService.db.collection("MedicationIntake")
                .whereEqualTo("uid", currentUserId)
                .whereEqualTo("medicationName", medication.name)
                .whereGreaterThan("dateTime", todayStart)
                .whereLessThan("dateTime", todayEnd)
                //.limit(1)
                .get(source)
                .await()

            if (!querySnapshot.isEmpty) {
                val intakes = querySnapshot.toObjects(UserIntake::class.java)
                val status = intakes.get(0).status

                ret = if (status == true) 2 else 1
            } else {
                ret = 0
            }
            // ret = querySnapshot.isEmpty
        } catch (e: Exception) {
            Log.e("DashboardVM", "Error fetching current medications: ${e.message}", e)
            println("Error fetching current medications: ${e.message}")
            ret = -1
        }
        return ret

    }

}