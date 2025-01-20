package com.galeria.medicationstracker.data

import com.google.firebase.Timestamp
import kotlinx.coroutines.flow.Flow

interface DrugRepository {
    
    fun getDrugsStream(): Flow<List<UserMedication>>
    
    suspend fun getDrugs(): List<UserMedication>
    
    suspend fun addDrug(
        endDate: Timestamp,
        startDate: Timestamp,
        medName: String,
        medForm: String,
        selectedDays: List<String>,
        intakeTime: String,
        notes: String,
        strength: Float,
        strengthUnit: String,
        uid: String
    )
    
    suspend fun updateDrug(
        endDate: Timestamp,
        startDate: Timestamp,
        medName: String,
        medForm: String,
        selectedDays: List<String>,
        intakeTime: String,
        notes: String,
        strength: Float,
        strengthUnit: String,
        uid: String
    )
    
    suspend fun deleteDrug(drugName: String)
    
}