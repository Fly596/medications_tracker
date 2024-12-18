package com.galeria.medicationstracker.ui.screens.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.vector.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import androidx.lifecycle.compose.*
import androidx.lifecycle.viewmodel.compose.*
import com.galeria.medicationstracker.data.*
import com.galeria.medicationstracker.ui.components.*
import com.galeria.medicationstracker.ui.theme.*
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme.typography

// TODO: Get medications from firebase DB.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    onMedicationLogsClick: () -> Unit = {},
    dashboardViewModel: DashboardVM = viewModel(),
) {
  // список лекарств.
  val currentMedications by dashboardViewModel.currentTakenMedications.collectAsStateWithLifecycle()

  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(16.dp),
  ) {

    TextField(
      value = "${currentMedications.size}",
      label = { Text("Current taken medications") },
      onValueChange = {},
      readOnly = true
    )

    // Календарь на неделю.
    WeeklyCalendarView()

    // Medication Cards List.
    MedsByIntakeTimeList(
      medicationsForIntakeTime = currentMedications
    )
  }
}

// Список лекарств по времени приема.
@Composable
fun MedsByIntakeTimeList(
    medicationsForIntakeTime: List<UserMedication> = emptyList()
) {

  // Группируем лекарства по времени приема.
  val medicationsByIntakeTime =
    medicationsForIntakeTime.groupBy { it.intakeTime }

  LazyColumn(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(24.dp)
  ) {

    medicationsByIntakeTime.forEach { (intakeTime, medications) ->
      item {
        // Контейнер для каждого времени приема.
        FLySimpleCardContainer(modifier = Modifier.fillMaxWidth()) {
          Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
          ) {

            // Время приема.
            Text(
              text = intakeTime.toString(),
              style = typography.headline,
              modifier = Modifier.padding(0.dp)
            )

            // Лекарства на это время.
            medications.forEach { medicationsForIntakeTime ->
              MedicationItem(
                medicationsForIntakeTime.name.toString()
              )

            }
          }
        }
      }

    }
  }
}

@Composable
fun MedicationItem(
    name: String,
    onLogMedClick: () -> Unit = {},
    icon: ImageVector = Icons.Filled.Medication
) {

  // State to control the visibility of the log medication dialog.
  val showLogDialog = remember { mutableStateOf(false) }

  Row(
    modifier = Modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    Icon(
      imageVector = icon,
      contentDescription = null,
      modifier = Modifier.size(32.dp)
    )

    Text(text = name, style = typography.headline)

    Spacer(modifier = Modifier.weight(1f))

    // State to control the check icon.
    var isChecked by remember { mutableStateOf(false) }

    IconButton(
      onClick = {
        // Add logic to log medication here.
        showLogDialog.value = !showLogDialog.value
        isChecked = !isChecked
      }
    ) {
      Icon(
        imageVector = if (isChecked) {
          Icons.Filled.CheckCircle
        } else {
          Icons.Outlined.CheckCircle
        },
        contentDescription = null,
        modifier = Modifier.size(32.dp),
        tint = if (isChecked) {
          MedTrackerTheme.colors.primary400
        } else {
          MedTrackerTheme.colors.tertiaryLabel
        }
      )
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
  }
}

@Preview(name = "StartScreen")
@Composable
private fun PreviewStartScreen() {
  // StartScreen("empty")
}
