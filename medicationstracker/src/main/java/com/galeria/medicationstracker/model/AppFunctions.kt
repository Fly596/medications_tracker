package com.galeria.medicationstracker.model

import java.time.*

public class AppFunctions() {
  
  fun checkDate(startDate: String, endDate: String): Boolean {
    val currentDate = LocalDate.now()
    val inputDateStart = parseDateString(startDate)
    val inputDateEnd = parseDateString(endDate)
    
    
    return (inputDateStart != null && inputDateEnd != null) && (inputDateEnd >= currentDate) && (inputDateStart <= inputDateEnd)
    
  }
}
