package com.galeria.medicationstracker.data

import android.icu.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.Date
import java.util.Locale

// Contains data from firestore db.
data class Medication(
    val uid: String,
    val name: String,
    val type: String,
    val form: MedicationForm = MedicationForm.TABLET,
    val strength: Float = 0.0f,
    val unit: MedicationUnit = MedicationUnit.MG,
    val startDate: LocalDate = LocalDate.now(),
    val endDate: LocalDate? = null,
    val frequency: Frequency = Frequency.AtRegularIntervals(0), // TODO: enum?
    val intakeTime: LocalTime = LocalTime.now(),
    val notes: String = "",
)

sealed class Frequency {
  data class AtRegularIntervals(val interval: Int) : Frequency()

  data class OnSpecificDaysOfTheWeek(val days: List<DayOfWeek>) : Frequency()
}

enum class MedicationForm {
  TABLET,
  CAPSULE,
  LIQUID,
  INJECTION,
}

enum class MedicationUnit {
  MG,
  ML,
  G,
}

val sdf = SimpleDateFormat("MMMM dd, yyyy", Locale.US)

data class TEMP_Medication(
    val uid: String = "",
    val name: String = "",
    val type: String = "",
    val dateTime: String? = sdf.format(Date())
)

/*TODO: Symptoms, mood..*/
data class MoodLog(
    val date: LocalDateTime = LocalDateTime.now()
    // TODO
)

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

/* data class UserMedication(
  val medication: Medication = Medication(),
  val notes: String = "",
  val startDate: LocalDate = LocalDate.now(),
  val endDate: LocalDate? = null,
  val frequency: String = "",
  val time: LocalTime = LocalTime.now(),
) */
