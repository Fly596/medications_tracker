package com.galeria.medicationstracker.ui.screens.medications.mediinfo

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import androidx.lifecycle.compose.*
import androidx.lifecycle.viewmodel.compose.*
import com.galeria.medicationstracker.ui.componentsOld.*
import com.galeria.medicationstracker.ui.screens.medications.*
import com.galeria.medicationstracker.ui.theme.*
import com.galeria.medicationstracker.utils.*
import com.google.firebase.*

@Composable
fun ViewMedicationInfoScreen(
    modifier: Modifier = Modifier,
    onReturn: () -> Unit = {},
    medsViewModel: MedsPagesViewModel = viewModel(),
) {
    val uiState by medsViewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        // имя и дни приема.
        MedInfoHeader(
            medName = uiState.selectedMed?.name ?: ""
        )
        // начальная дата, total taken/skipped.
        MedStatCard(
            startDate = uiState.selectedMed?.startDate,
            totalTaken = uiState.intakesCount,
            totalSkipped = uiState.skipCount
        )
        FlyTonalButton(
            onClick = onReturn
        ) {
            Text(text = "Return")
        }
    }

}

@Composable
fun MedStatCard(
    modifier: Modifier = Modifier,
    startDate: Timestamp? = null,
    totalTaken: Int,
    totalSkipped: Int
) {
    if (startDate == null) return
    val startDateFormatted = formatTimestampTillTheDay(startDate)

    FlySimpleCard(modifier = modifier) {
        // начальная дата.
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Start Date", style = MedTrackerTheme.typography.bodyMedium)
            Text(startDateFormatted, style = MedTrackerTheme.typography.title3Emphasized)
        }
        HorizontalDivider(color = MedTrackerTheme.colors.opaqueSeparator, thickness = 0.5.dp)

        MedStatCardBody(totalTaken, totalSkipped)
    }
}

@Composable
fun MedStatCardBody(totalTaken: Int, totalSkipped: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Total Taken", style = MedTrackerTheme.typography.bodyMedium)
            Text(
                "${totalTaken} times",
                style = MedTrackerTheme.typography.title2Emphasized
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Total Skipped", style = MedTrackerTheme.typography.bodyMedium)
            Text(
                "${totalSkipped} times",
                style = MedTrackerTheme.typography.title2Emphasized
            )
        }
    }
}

@Composable
fun MedInfoHeader(
    modifier: Modifier = Modifier,
    medName: String = "Oxycodone"
) {
    Column(modifier.padding(vertical = 16.dp)) {
        Text(text = medName, style = MedTrackerTheme.typography.title1Emphasized)
    }
}

@Preview(backgroundColor = 0xFFF2F2F7, showBackground = true)
@Composable
fun ViewMedicationInfoScreenPreview() {
    MedTrackerTheme {
        ViewMedicationInfoScreen(
        )
    }

}