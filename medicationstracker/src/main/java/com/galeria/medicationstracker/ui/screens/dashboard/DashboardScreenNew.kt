package com.galeria.medicationstracker.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.RadioButtonUnchecked
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.galeria.medicationstracker.data.UserIntake
import com.galeria.medicationstracker.data.UserMedication
import com.galeria.medicationstracker.ui.components.GPrimaryButton
import com.galeria.medicationstracker.ui.components.GSecondaryButton
import com.galeria.medicationstracker.ui.componentsOld.FLySimpleCardContainer
import com.galeria.medicationstracker.ui.componentsOld.WeeklyCalendarView
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme.typography
import com.galeria.medicationstracker.utils.getTodaysDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreenNew(
    modifier: Modifier = Modifier,
    /*
        onIntakeClick: () -> Unit, */
    dashboardViewModel: DashboardScreenViewModelNew = hiltViewModel(),
) {
    val uiState = dashboardViewModel.uiState.collectAsStateWithLifecycle()
    val medicationsByIntakeTime = uiState.value.currentTakenMedications.groupBy { it.intakeTime }
    val showLogDialog = rememberSaveable { mutableStateOf(false) }
    var selectedTime: TimePickerState? by remember { mutableStateOf(null) }
    
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = getTodaysDate().format(DateTimeFormatter.ofPattern("MMM d")),
            style = typography.display3
        )
        // Календарь на неделю.
        WeeklyCalendarView()
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            medicationsByIntakeTime.forEach { (intakeTime, medications) ->
                item {
                    FLySimpleCardContainer {
                        Column {
                            Text(text = intakeTime.toString(), style = typography.title1)
                            medications.forEach { medication ->
                                MedicationIntakeCard(
                                    medication = medication,
                                    todayIntakes = uiState.value.todayIntakes,
                                    onIntakeClick = {
                                        showLogDialog.value = !showLogDialog.value
                                        // dashboardViewModel.addNewIntake(medication = it)
                                    }
                                )
                                if (showLogDialog.value) {
                                    IntakeTimeDialog(
                                        onDismiss = {
                                            showLogDialog.value = false
                                        },
                                        onConfirm = { time ->
                                            selectedTime = time
                                            val timeStamp =
                                                dashboardViewModel.timeToFirestoreTimestamp(
                                                    time.hour,
                                                    time.minute
                                                )
                                            dashboardViewModel.updateTime(timeStamp)
                                            showLogDialog.value = false
                                            dashboardViewModel.addNewIntake(
                                                intakeTime = timeStamp,
                                                medication = medication
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntakeTimeDialog(
    onConfirm: (TimePickerState) -> Unit,
    onDismiss: () -> Unit,
) {
    val currentTime = Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )
    Column {
        TimePicker(
            state = timePickerState,
        )
        GSecondaryButton(onClick = onDismiss) {
            Text("Dismiss picker")
        }
        GPrimaryButton(onClick = { onConfirm(timePickerState) }) {
            Text("Confirm selection")
        }
    }
}

@Composable
fun MedicationIntakeCard(
    modifier: Modifier = Modifier,
    medication: UserMedication,
    todayIntakes: List<UserIntake>,
    onIntakeClick: (UserMedication) -> Unit
) {
    val isTaken = todayIntakes.any { it.medicationName == medication.name }
    
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(if (isTaken) Color.Green.copy(alpha = 0.3f) else Color.Transparent)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            // Text(text = medication.intakeTime.toString(), style = MedTrackerTheme.typography.title1)
            Text(text = medication.name.toString(), style = MedTrackerTheme.typography.title1)
        }
        IconButton(
            onClick = { onIntakeClick(medication) },
            enabled = !isTaken // Disable button if already taken
        ) {
            Icon(
                imageVector = if (isTaken) Icons.Filled.CheckCircle else Icons.Outlined.RadioButtonUnchecked,
                contentDescription = null
            )
        }
    }
}

@Composable
fun MedsByIntakeTimeListNew(
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
                            style = typography.title1,
                            modifier = Modifier.padding(0.dp)
                        )
                        // Лекарства на это время.
                        medications.forEach { medicationsForIntakeTime ->
                            MedicationIntakeCard()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MedicationIntakeCard(
    modifier: Modifier = Modifier,
    medicationName: String = "",
    intakeTime: String = "00:00"
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier) {
            Text(text = intakeTime, style = MedTrackerTheme.typography.title1)
            Text(text = medicationName, style = MedTrackerTheme.typography.title1)
        }
        IconButton(
            onClick = {
                // TODO: check intake.
            }
        ) {
            Icon(
                Icons.Filled.CheckCircle,
                contentDescription = null,
            )
        }
    }
}

@Preview
@Composable
fun DashboardScreenNewPreview() {
    MedTrackerTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            // DashboardScreenNew(onIntakeClick = {})
        }
    }
}