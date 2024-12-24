package com.galeria.medicationstracker.ui.screens.medications.mediinfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.galeria.medicationstracker.ui.components.FlySimpleCard
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

@Composable
fun ViewMedicationInfoScreen(
    modifier: Modifier = Modifier,
    // viewMedVM: ViewMedVM = viewModel(),
) {
    Column(modifier = modifier.fillMaxWidth()) {
        // имя и дни приема.
        MedInfoHeader()

        // начальная дата, total taken/skipped.
        MedStatCard()
    }

}

@Composable
fun MedStatCard(
    modifier: Modifier = Modifier,
    startDate: String = "Dec 07, 2024"
) {
    FlySimpleCard(modifier = modifier) {
        // начальная дата.
        Column() {
            Text("Start Date")
            Text(startDate, style = MedTrackerTheme.typography.title3Emphasized)
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