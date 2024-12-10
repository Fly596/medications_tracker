package com.galeria.medicationstracker.data

import android.icu.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.util.Locale

data class UserMedication(
  val uid: String? = null,
  val name: String? = null,
  val form: String? = null,
  val strength: Float? = null,
  val unit: String? = null,
  val startDate: String? = null,
  val endDate: String? = null,
  val intakeTime: String? = null,
  val notes: String? = null,
)


// Contains data from firestore db.
/* data class Medication(
  val uid: String,
  val name: String,
  val type: String,
  val form: MedicationForms = MedicationForms.TABLET,
  val strength: Float,
  val unit: MedicationUnit = MedicationUnit.MG,

  val startDate: LocalDate = LocalDate.now(),
  val endDate: LocalDate? = null,
  val frequency: Frequency = Frequency.AtRegularIntervals(0), // TODO: enum?
  val intakeTime: LocalTime,
  val notes: String = "",
) */

sealed class Frequency {
  data class AtRegularIntervals(val interval: Int = 0) : Frequency()

  data class OnSpecificDaysOfTheWeek(val days: List<DayOfWeek> = DayOfWeek.entries) :
    Frequency()
}

enum class MedicationForms {
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
