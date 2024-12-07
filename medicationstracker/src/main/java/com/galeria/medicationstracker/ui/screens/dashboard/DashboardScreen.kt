package com.galeria.medicationstracker.ui.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.ui.screens.medications.NavigationRow
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

// TODO: Get medications from firebase DB.
@Composable
fun DashboardScreen(
  modifier: Modifier = Modifier,
  viewModel: DashboardVM = viewModel(),
) {
  val state = viewModel.uiState

  Column(
    modifier = modifier.fillMaxSize(),
    verticalArrangement = Arrangement.spacedBy(16.dp),
  ) {

    // Screen header with title
    Text(
      "Today",
      style = MedTrackerTheme.typography.largeTitleEmphasized,
    )
    // Space between header and profile info.
    Spacer(modifier = Modifier.padding(vertical = 8.dp))

    // TODO: Weekly calendar with medication records.

    LazyColumn(
      modifier = modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      items(4) {
        DashboardCardComponent(onAddMedicationRecordClick = {/*TODO: */ })
      }

    }
  }
}


@Composable
fun DashboardCardComponent(
  modifier: Modifier = Modifier,
  title: String = "Medication name",
  dosage: String = "Medication strength",
  actionLabel: String? = "Add record",
  medicationSchedule: String = "Mon, Tue, Fri...",
  medicationIntakeTime: String = "12:00PM",
  onAddMedicationRecordClick: () -> Unit,
) {
  ElevatedCard(modifier = modifier) {
    // TODO: можно обнести Row и добавить картинку (таблетка, pill, etc.).

    // Content of the card.
    Column(
      modifier =
        Modifier.padding(
          horizontal = dimensionResource(R.dimen.card_content_padding_horizontal),
          vertical = dimensionResource(R.dimen.card_content_padding_vertical),
        ),
      verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
    ) {
      // Medication title, dosage, and action.
      Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
      ) {
        // Title and action (e.g., "Add record").
        Row(modifier = Modifier.fillMaxWidth()) {
          Text(title, style = MedTrackerTheme.typography.headline)
          Spacer(modifier.weight(1f))
          // Navigation for adding a record.
          NavigationRow({ onAddMedicationRecordClick() }, actionLabel ?: "")
        }
        // Displays the medication dosage.
        Text(dosage, style = MedTrackerTheme.typography.subhead)
      }
      HorizontalDivider(color = MedTrackerTheme.colors.opaqueSeparator)

      Row(modifier = Modifier.fillMaxWidth()) {
        // Displays the medication schedule.
        Text(medicationSchedule, style = MedTrackerTheme.typography.body)
        Spacer(modifier = Modifier.weight(1f))
        Text(medicationIntakeTime, style = MedTrackerTheme.typography.body)

      }
    }
  }
}

@Preview(name = "StartScreen")
@Composable
private fun PreviewStartScreen() {
  // StartScreen("empty")
}
