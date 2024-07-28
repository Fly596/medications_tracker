package com.galeria.medicationstracker.data

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class Users(
    val name: String = "",
    val email: String = "",
    val pfp: String = "" /* url to pic*/,
    val born: String = "" /*yyyy-MM-dd*/,
    val notificationPreferences: NotificationPreferences = NotificationPreferences()
)

data class NotificationPreferences(
    val sound: Boolean = true,
    val vibration: Boolean = true,
)

data class UserMedication(
    /*    val name: String = "",
        val description: String = "",
        val form: String = "",
        val strength: Map<Int, String> = mapOf(
            0 to ""
        ),*/
    val medication: Medication = Medication(),
    val notes: String = "",
    val startDate: LocalDate = LocalDate.now(),
    val endDate: LocalDate? = null,
    val frequency: String = "",
    val time: LocalTime = LocalTime.now(),
)

// Contains data from firestore db.
data class Medication(
    val name: String = "",
    val description: String = "",
    val form: String = "",
    val strength: Map<Int, String> = mapOf()
)

/*TODO: Symptoms, mood..*/
data class MoodLog(
    val date: LocalDateTime = LocalDateTime.now()
    // TODO
)

