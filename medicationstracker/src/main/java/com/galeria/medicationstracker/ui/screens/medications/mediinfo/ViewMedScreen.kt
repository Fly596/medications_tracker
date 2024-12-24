package com.galeria.medicationstracker.ui.screens.medications.mediinfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.model.formatTimestampTillTheDay
import com.galeria.medicationstracker.ui.components.FlySimpleCard
import com.galeria.medicationstracker.ui.components.FlyTextButton
import com.galeria.medicationstracker.ui.screens.medications.MedsPagesViewModel
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme
import com.google.firebase.Timestamp

@Composable
fun ViewMedicationInfoScreen(
    modifier: Modifier = Modifier,
    onReturn: () -> Unit = {},
    medsViewModel: MedsPagesViewModel = viewModel(),
) {
    val uiState by medsViewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = modifier.fillMaxWidth()) {
        FlyTextButton(
            onClick = onReturn
        ) {
            Text(text = "Return")
        }
        // имя и дни приема.
        MedInfoHeader(
            medName = uiState.selectedMed?.name ?: ""
        )


        TextField(
            value = uiState.selectedMed?.name ?: "",
            onValueChange = {}
        )
        /*         FlyTextField(
                    value = uiState.selectedMed?.name ?: "",
                    readOnly = True,
                    onValueChange = {
                        medsViewModel.getSelectedMed(it).toString()
                    }
                ) */

        // начальная дата, total taken/skipped.
        MedStatCard(
            startDate = uiState.selectedMed?.startDate
        )
    }

}

@Composable
fun MedStatCard(
    modifier: Modifier = Modifier,
    startDate: Timestamp? = null,
) {
    if (startDate == null) return
    val startDateFormatted = formatTimestampTillTheDay(startDate)
    FlySimpleCard(modifier = modifier) {
        // начальная дата.
        Column() {
            Text("Start Date")
            Text(startDateFormatted, style = MedTrackerTheme.typography.title3Emphasized)
        }

        MedStatCardBody()

    }
}

@Composable
fun MedStatCardBody(totalTaken: Int = 10, totalSkipped: Int = 0) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("Total Taken")
            Text(
                totalTaken.toString(),
                style = MedTrackerTheme.typography.title2
            )
        }
        Column {
            Text("Total Skipped")
            Text(
                totalSkipped.toString(),
                style = MedTrackerTheme.typography.title2

            )
        }
    }
}

@Composable
fun MedInfoHeader(
    modifier: Modifier = Modifier,
    medName: String = "Oxycodone"
) {
    Column(modifier.padding(16.dp)) {
        Text(text = medName, style = MedTrackerTheme.typography.title2Emphasized)
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