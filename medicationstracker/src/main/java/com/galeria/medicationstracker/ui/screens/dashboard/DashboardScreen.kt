package com.galeria.medicationstracker.ui.screens.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.painter.*
import androidx.compose.ui.graphics.vector.*
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import androidx.lifecycle.compose.*
import androidx.lifecycle.viewmodel.compose.*
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.data.*
import com.galeria.medicationstracker.ui.componentsOld.*
import com.galeria.medicationstracker.ui.theme.*
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme.typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    onViewLogsClick: () -> Unit = {},
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
        // TODO: ОБНОВИТЬ ПУТЬ К ЛОГАМ.
        FlyButton(
            onClick = {
                onViewLogsClick.invoke()
            }
        ) {
            Text("View Logs")
        }
        
        ServicesElements()
        // Medication Cards List.
        MedsByIntakeTimeList(
            viewModel = dashboardViewModel,
            medicationsForIntakeTime = currentMedications
        )
    }
}

@Composable
fun ServicesElements() {
    Column(modifier = Modifier) {
        Text(
            text = "Services",
            style = MedTrackerTheme.typography.title2Emphasized,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            ServiceCard(
                painterResource(R.drawable.doctor_logo)
            )
            ServiceCard(
                painterResource(R.drawable.pill_logo)
            )
            ServiceCard(
                painterResource(R.drawable.schedule_icon)
            )
        }
    }
    
}

@Composable
fun ServiceCard(icon: Painter) {
    Card(
        modifier = Modifier
            .size(76.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MedTrackerTheme.colors.primaryTinted,
            contentColor = MedTrackerTheme.colors.primaryLabel
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(44.dp),
                painter = icon,
                contentDescription = null,
                tint = MedTrackerTheme.colors.secondary600,
            )
        }
    }
    
}

// Список лекарств по времени приема.
@Composable
fun MedsByIntakeTimeList(
    viewModel: DashboardVM,
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
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(32.dp)
        )
        
        Text(text = medication.name.toString(), style = typography.headline)
        
        Spacer(modifier = Modifier.weight(1f))
        // State to control the check icon.
        var status by remember { mutableStateOf(0) }
        LaunchedEffect(medication) {
            status = viewModel.checkIntake(medication)
        }
        
        Text(
            text = if (status == 2) "Taken" else if (status == 1) "Skipped" else "",
            style = typography.bodySmall,
            color = MedTrackerTheme.colors.secondaryLabel
        )
        
        IconButton(
            onClick = {
                // Add logic to log medication here.
                showLogDialog.value = !showLogDialog.value
            }) {
            Icon(
                imageVector = if (status == 2) {
                    // taken
                    Icons.Filled.CheckCircle
                } else if (status == 1) {
                    // skipped
                    Icons.Filled.CheckCircle
                } else {
                    // nodata
                    Icons.Outlined.CheckCircle
                },
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = if (status == 2) {
                    MedTrackerTheme.colors.sysSuccess
                } else if (status == 1) {
                    MedTrackerTheme.colors.sysWarning
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
                    viewModel.addNewIntake(
                        medication = medication,
                        status = false
                    )
                    showLogDialog.value = false
                },
                onConfirmation = {
                    viewModel.addNewIntake(
                        medication = medication,
                        status = true
                    )
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
