package com.galeria.medicationstracker.ui.screens.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.*
import com.galeria.medicationstracker.data.*
import com.galeria.medicationstracker.ui.components.*
import com.google.firebase.*
import java.time.*
import java.time.format.*

// TODO: Get medications from firebase DB.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
  modifier: Modifier = Modifier,
  onLogsClick: () -> Unit = {},
  viewModel: DashboardVM = viewModel(),
) {
  // Get today's date.
  val currentDate = LocalDate.now()
  val dateFormatter = DateTimeFormatter.ofPattern("MMM dd")
  val formattedCurrentDate = currentDate.format(dateFormatter)
  
  val showLogDialog = remember { mutableStateOf(false) }
  
  Column(
    modifier = modifier
      .padding(horizontal = 16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),
    
    ) {
/*

    Button(
      onClick = { showLogDialog.value = !showLogDialog.value }
    ) {
      Text("Dialog component with an image")
    }

    // Display the dialog when `showLogDialog.value` is true
    if (showLogDialog.value) {
      LogMedicationTimeDialog(
        onDismissRequest = { showLogDialog.value = false },
        onConfirmation = {
          showLogDialog.value = false
        }
      )
    }
*/
    
    Spacer(modifier = Modifier.padding(vertical = 8.dp))
    
    WeeklyCalendarView()
    
    // TODO: Replace with medication data from firebase DB.
    val medicationsTest = listOf(
      UserMedication(
        uid = "001",
        name = "Paracetamol",
        form = "Tablet",
        strength = 500f,
        unit = "mg",
        startDate = Timestamp.now(),
        endDate = Timestamp.now(),
        frequency = "Twice a day",
        intakeTime = "9:25 AM",
        notes = "Take after meals"
      ),
      
      UserMedication(
        uid = "002",
        name = "Amoxicillin",
        form = "Capsule",
        strength = 250f,
        unit = "mg",
        startDate = Timestamp.now(),
        endDate = Timestamp.now(),
        frequency = "Three times a day",
        intakeTime = "08:00 AM",
        notes = "Finish the entire course"
      ),
      
      UserMedication(
        uid = "003",
        name = "Ibuprofen",
        form = "Syrup",
        strength = 100f,
        unit = "mg/5ml",
        startDate = Timestamp.now(),
        endDate = Timestamp.now(),
        frequency = "Once a day",
        intakeTime = "9:25 AM",
        notes = "Shake well before use"
      ),
    )
    
    val groupedMeds = medicationsTest.groupBy { it.intakeTime }
    
    // Medication Cards List.
    FlyCardMedsByTimeList(
      medications = medicationsTest
    )
    
  }
  
  
}

@Preview(name = "StartScreen")
@Composable
private fun PreviewStartScreen() {
  // StartScreen("empty")
}
