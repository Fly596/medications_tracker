package com.galeria.medicationstracker.ui.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.data.UserMedication
import com.galeria.medicationstracker.ui.components.FLySimpleCardContainer
import com.galeria.medicationstracker.ui.components.FlyButton
import com.galeria.medicationstracker.ui.components.LogMedicationTimeDialog
import com.galeria.medicationstracker.ui.components.WeeklyCalendarView
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme.typography

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
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        // Календарь на неделю.
        WeeklyCalendarView()

        // логи.
        FlyButton(
            onClick = {
                onMedicationLogsClick.invoke()
            }
        ) {
            Text("View Logs")
        }

        // Medication Cards List.
        MedsByIntakeTimeList(
            viewModel = dashboardViewModel, medicationsForIntakeTime = currentMedications
        )
    }
}

// Список лекарств по времени приема.
@Composable
fun MedsByIntakeTimeList(
    viewModel: DashboardVM, medicationsForIntakeTime: List<UserMedication> = emptyList()
) {

    // Группируем лекарства по времени приема.
    val medicationsByIntakeTime = medicationsForIntakeTime.groupBy { it.intakeTime }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(24.dp)
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
                                viewModel = viewModel,
                                medication = medicationsForIntakeTime,
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
    viewModel: DashboardVM,
    medication: UserMedication,
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
            imageVector = icon, contentDescription = null, modifier = Modifier.size(32.dp)
        )

        Text(text = medication.name.toString(), style = typography.headline)

        Spacer(modifier = Modifier.weight(1f))

        // State to control the check icon.
        var isChecked by remember { mutableStateOf(false) }
        LaunchedEffect(medication) {
            isChecked = viewModel.checkIntake(medication)
        }

        IconButton(
            onClick = {
                // Add logic to log medication here.
                showLogDialog.value = !showLogDialog.value
                isChecked = !isChecked
            }) {
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
                viewModel,
                onDismiss = {
                    viewModel.recordMedicationIntake(medication = medication, status = false)
                    showLogDialog.value = false
                },
                onConfirmation = {
                    viewModel.recordMedicationIntake(medication = medication, status = true)
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
