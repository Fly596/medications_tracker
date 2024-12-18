package com.galeria.medicationstracker.model

import com.google.firebase.*
import java.text.*
import java.time.*
import java.time.format.*
import java.util.*

fun getStringFormattedDate(inputDate: LocalDate): String {
  val dateFormatter = DateTimeFormatter.ofPattern("MMM dd")
  val formattedCurrentDate = inputDate.format(dateFormatter)

  return formattedCurrentDate.toString()
}

fun parseDateString(dateString: String): LocalDate {
  return try {
    val formatter = DateTimeFormatter.ofPattern("MMM dd")

    LocalDate.parse(dateString, formatter)
  } catch (e: Exception) {

    // Handle parsing errors, e.g., log the error or return null
    LocalDate.now()
  }
}

fun parseDateForFirestore(dateString: String): Timestamp? {
  return try {
    val formatter = SimpleDateFormat("MMMM dd yyyy", Locale.getDefault())
    val date = formatter.parse(dateString)
    if (date != null) {
      Timestamp(date)
    } else {
      null
    }
  } catch (e: Exception) {
    // Handle parsing errors (e.g., log the error)
    null
  }
}

fun formatTimestamp(timestamp: Timestamp): String {
  val formatter = SimpleDateFormat("MMMM dd yyyy", Locale.getDefault())
  return formatter.format(timestamp.toDate())
}

fun LocalDateTime.toTimestamp() = Timestamp(atZone(ZoneId.systemDefault()).toEpochSecond(), nano)
fun Timestamp.toLocalDateTime(zone: ZoneId = ZoneId.systemDefault()) =
  LocalDateTime.ofInstant(Instant.ofEpochMilli(seconds * 1000 + nanoseconds / 1000000), zone)
