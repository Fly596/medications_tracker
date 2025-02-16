package com.galeria.medicationstracker.ui.screens.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galeria.medicationstracker.data.UserIntake
import com.galeria.medicationstracker.data.UserMedication
import com.galeria.medicationstracker.utils.FirestoreFunctions.FirestoreService
import com.galeria.medicationstracker.utils.formatTimestampToWeekday
import com.galeria.medicationstracker.utils.toLocalDateTime
import com.galeria.medicationstracker.utils.toTimestamp
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Source
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

class DashboardVM() : ViewModel() {
    
    val db = FirestoreService.db
    
    // Лекарства, которые нужно принимать.
    private val _currentTakenMedications =
        MutableStateFlow<List<UserMedication>>(emptyList())
    val currentTakenMedications = _currentTakenMedications.asStateFlow()
    val firebaseAuth = FirebaseAuth.getInstance()
    val currentUserId = firebaseAuth.currentUser?.uid
    private val _intakeStatus = MutableStateFlow(0)
    val intakeStatus: StateFlow<Int> = _intakeStatus.asStateFlow()
    
    
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
    private fun getCurrentMedications() {
        val todayEnd = LocalDate.now().plusDays(1).atStartOfDay().toTimestamp()
        val todayWeekDay = formatTimestampToWeekday(Timestamp.now()).uppercase()
        
        viewModelScope.launch {
            db.collection("UserMedication")
                .whereEqualTo("uid", currentUserId)
                .whereGreaterThanOrEqualTo("endDate", todayEnd)
                .whereArrayContains("daysOfWeek", todayWeekDay)
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
    
    fun addNewIntake(
        intakeTime: Timestamp = Timestamp.now(),
        medication: UserMedication = UserMedication(),
        status: Boolean = true
    ) {
        val intake = UserIntake(
            uid = currentUserId.toString(),
            medicationName = medication.name.toString(),
            dose = medication.strength.toString(),
            status = status,
            dateTime = intakeTime
        )
        
        FirestoreService.db.collection("User")
            .document("${FirebaseAuth.getInstance().currentUser?.email}")
            .collection("intakes")
            .document("${medication.name}_${intakeTime.toLocalDateTime().dayOfYear}")
            .set(
                intake
            )
        
        
    }
    
    // Проверка на то, был ли сегодня прием или нет.
    // -1: error; 0: noData, 1: skipped, 2: taken
    fun checkIntake(medication: UserMedication) {
        viewModelScope.launch {
            val status = fetchIntakeStatus(medication)
            _intakeStatus.value = status
        }
    }
    /*     private suspend fun fetchIntakeStatus(medication: UserMedication): Int {
            val todayStart = LocalDate.now().atStartOfDay().toTimestamp()
            val todayEnd = LocalDate.now().plusDays(1).atStartOfDay().toTimestamp()
            return try {
                val querySnapshot = db.collection("User")
                    .document("${FirebaseAuth.getInstance().currentUser?.email}")
                    .collection("intakes")
                    .whereEqualTo("medicationName", medication.name)
                    .whereGreaterThanOrEqualTo("dateTime", todayStart)
                    .whereLessThan("dateTime", todayEnd)
                    .limit(1)
                    .get(Source.SERVER)
                    .await()
                
                if (!querySnapshot.isEmpty) {
                    if (querySnapshot.toObjects(UserIntake::class.java)[0].status == true) 2 else 1
                } else {
                    0
                }
            } catch (e: Exception) {
                Log.e("checkIntake", "Error fetching intake data", e)
                -1
            }
        } */
    suspend fun fetchIntakeStatus(medication: UserMedication): Int {
        val todayStart = LocalDate.now().atStartOfDay().toTimestamp()
        val todayEnd = LocalDate.now().plusDays(1).atStartOfDay().toTimestamp()
        var ret = -1
        val source = Source.DEFAULT
        return try {
            val querySnapshot = db.collection("User")
                .document("${FirebaseAuth.getInstance().currentUser?.email}")
                .collection("intakes")
                .whereEqualTo("medicationName", medication.name)
                .whereGreaterThanOrEqualTo("dateTime", todayStart)
                .whereLessThan("dateTime", todayEnd)
                .limit(1)
                .get(Source.SERVER)
                .await()
            
            if (!querySnapshot.isEmpty) {
                if (querySnapshot.toObjects(UserIntake::class.java)[0].status == true) 2 else 1
            } else {
                0
            }
        } catch (e: Exception) {
            Log.e("checkIntake", "Error fetching intake data", e)
            -1
        }
        
    }
    
}