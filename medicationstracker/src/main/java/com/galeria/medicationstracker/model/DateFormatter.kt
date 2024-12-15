package com.galeria.medicationstracker.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun getFormattedDate(date: LocalDate): String {
  val dateFormatter = DateTimeFormatter.ofPattern("MMM dd")
  val formattedCurrentDate = date.format(dateFormatter)

  return formattedCurrentDate.toString()
}