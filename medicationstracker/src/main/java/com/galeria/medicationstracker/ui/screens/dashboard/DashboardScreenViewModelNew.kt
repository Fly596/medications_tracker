package com.galeria.medicationstracker.ui.screens.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galeria.medicationstracker.data.UserIntake
import com.galeria.medicationstracker.data.UserMedication
import com.galeria.medicationstracker.data.UserRepository
import com.galeria.medicationstracker.utils.FirestoreFunctions.FirestoreService.db
import com.galeria.medicationstracker.utils.formatTimestampToWeekday
import com.galeria.medicationstracker.utils.toTimestamp
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

data class DashboardScreenUiState(
    val currentTakenMedications: List<UserMedication> = emptyList(),
    val todayIntakes: List<UserIntake> = emptyList(),
    val time: Timestamp = Timestamp.now()
)

@HiltViewModel
class DashboardScreenViewModelNew @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    
    val userId = FirebaseAuth.getInstance().currentUser?.uid
    private val _uiState = MutableStateFlow(DashboardScreenUiState())
    val uiState: StateFlow<DashboardScreenUiState> = _uiState.asStateFlow()
    
    init {
        // Получение списка активных лекарств пациента.
        getCurrentMedications()
        getTodaysIntakes()
    }
    
    fun updateTime(time: Timestamp) {
        _uiState.value = _uiState.value.copy(time = time)
    }
    
    fun addNewIntake(
        intakeTime: Timestamp = Timestamp.now(),
        medication: UserMedication = UserMedication(),
        status: Boolean = true
    ) {
        viewModelScope.launch {
            val intake = UserIntake(
                uid = userId.toString(),
                medicationName = medication.name.toString(),
                dose = medication.strength.toString(),
                status = status,
                dateTime = _uiState.value.time
            )
            repository.addIntake(intake)
        }
        
    }
    
    private fun getTodaysIntakes() {
        val todayStart = LocalDate.now()
            .atStartOfDay()
            .toTimestamp()
        val todayEnd = LocalDate.now()
            .plusDays(1)
            .atStartOfDay()
            .toTimestamp()
        
        viewModelScope.launch {
            db.collection("MedicationIntake")
                .whereEqualTo("uid", userId)
                .whereGreaterThanOrEqualTo("dateTime", todayStart)
                .whereLessThan("dateTime", todayEnd)
                .addSnapshotListener { intakeSnapshots, error ->
                    if (error != null) {
                        Log.e("DashboardVM", "Error fetching intakes: ${error.message}", error)
                        return@addSnapshotListener
                    }
                    
                    intakeSnapshots?.let {
                        _uiState.value = _uiState.value.copy(
                            todayIntakes = it.toObjects(UserIntake::class.java)
                        )
                    }
                }
        }
    }
    
    // Фильтрация лекарств, прием которых окончен для использования при выводе на главный экран.
    private fun getCurrentMedications() {
        val todayEnd = LocalDate.now()
            .plusDays(1)
            .atStartOfDay()
            .toTimestamp()
        val todayWeekDay = formatTimestampToWeekday(Timestamp.now()).uppercase()
        
        viewModelScope.launch {
            db.collection("UserMedication")
                .whereEqualTo("uid", userId)
                .whereGreaterThanOrEqualTo("endDate", todayEnd)
                .whereArrayContains("daysOfWeek", todayWeekDay)
                .addSnapshotListener { medicationSnapshots, error ->
                    if (error != null) {
                        Log.e(
                            "DashboardVM",
                            "Error fetching current medications: ${error.message}",
                            error
                        )
                        return@addSnapshotListener
                    }
                    
                    medicationSnapshots?.let {
                        _uiState.value = _uiState.value.copy(
                            currentTakenMedications = it.toObjects(UserMedication::class.java)
                        )
                    }
                }
        }
        
    }
    /*     fun timeToFirestoreTimestamp(hour: Int, minute: Int): Timestamp {
            val now = LocalDate.now() // Get current date
            val localDateTime = LocalDateTime.of(now, LocalTime.of(hour, minute))
            return Timestamp(
                localDateTime.atZone(ZoneId.systemDefault())
                    .toEpochSecond(), 0
            )
        } */
    
}