package com.galeria.medicationstracker.utils

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun getTodaysDate(): LocalDate {
    // Gets the current date using the system's default time zone.
    return LocalDate.now(ZoneId.systemDefault())
}

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

fun formatTimestampTillTheDay(timestamp: Timestamp): String {
    val formatter = SimpleDateFormat("MMMM dd yyyy", Locale.getDefault())
    return formatter.format(timestamp.toDate())
}

fun formatTimestampTillTheHour(timestamp: Timestamp): String {
    val formatter = SimpleDateFormat("K:mm a", Locale.getDefault())
    return formatter.format(timestamp.toDate())
}

fun formatTimestampTillTheSec(timestamp: Timestamp): String {
    val formatter = SimpleDateFormat("MMMM dd yyyy, H m", Locale.getDefault())
    return formatter.format(timestamp.toDate())
}

fun formatTimestampToWeekday(timestamp: Timestamp): String {
    val dayOfWeekFormatter =
        DateTimeFormatter.ofPattern("EEEE") // Add day of week formatter

    return timestamp.toLocalDateTime().format(dayOfWeekFormatter)
}

fun formatTimestampToDay(timestamp: Timestamp): String {
    val dayOfWeekFormatter =
        DateTimeFormatter.ofPattern("MMMM dd yyyy") // Add day of week formatter

    return timestamp.toLocalDateTime().format(dayOfWeekFormatter)
}

fun LocalDateTime.toTimestamp() = Timestamp(atZone(ZoneId.systemDefault()).toEpochSecond(), nano)
fun Timestamp.toLocalDateTime(zone: ZoneId = ZoneId.systemDefault()) =
    LocalDateTime.ofInstant(Instant.ofEpochMilli(seconds * 1000 + nanoseconds / 1000000), zone)

fun getTodaysDateInMMMMddyyyyFormat(): LocalDate {
    val today = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy", Locale.getDefault())
    return formatter.parse(formatter.format(today), LocalDate::from)
}

fun addOneDayToDate(date: LocalDate, daysToAdd: Long = 1): LocalDate {
    val res = date.plusDays(daysToAdd)
    return res
}

fun timeToFirestoreTimestamp(hour: Int, minute: Int): Timestamp {
    val now = LocalDate.now() // Get current date
    val localDateTime = LocalDateTime.of(now, LocalTime.of(hour, minute))
    return Timestamp(
        localDateTime.atZone(ZoneId.systemDefault())
            .toEpochSecond(), 0
    )
}