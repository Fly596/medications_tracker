package com.galeria.medicationstracker.data

import android.icu.text.*
import com.google.firebase.*
import java.time.*
import java.util.*

data class UserMedication(
    val uid: String? = null,
    val name: String? = null,
    val form: String? = null,
    val strength: Float? = null,
    val unit: String? = null,
    val startDate: Timestamp? = null,
    val endDate: Timestamp? = null,
    val frequency: List<String> = emptyList(),
    val intakeTime: String? = null,
    val notes: String? = null,
)

data class HospitalDrugs(
    val name: String? = null, // Drisdol.
    val drugClass: String? = null, // vitamins d derivatives.
    val availability: String? = null, // prescription sometimes needed.
    val strength: Float? = null, // 1.25.
    val unit: String? = null, // MG.
    val form: String? = null, // capsules.
)

data class User(
    val uid: String,
    val login: String,
    val type: UserType = UserType.PATIENT
)

data class UserIntake(
    val uid: String,
    val medicationName: String,
    val dose: String,
    val status: Boolean,
    val dateTime: Timestamp,
)

enum class UserType {
  ADMIN,
  PATIENT,
  DOCTOR,
}

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
  POWDER
}

enum class MedicationUnit {
  MG,
  MCG,
  G,
  ML,
  OZ,
}

val sdf = SimpleDateFormat("MMMM dd, yyyy", Locale.US)

/*TODO: Symptoms, mood..*/
data class MoodLog(
    val date: LocalDateTime = LocalDateTime.now()
  // TODO
)

/* data class Users(
  val name: String = "",
  val email: String = "",
  val pfp: String = ""  *//* url to pic*//* ,
  val born: String = ""  *//*yyyy-MM-dd*//* ,
  val notificationPreferences: NotificationPreferences = NotificationPreferences()
) */

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
