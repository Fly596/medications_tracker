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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.ui.components.FlyTextButton
import com.galeria.medicationstracker.ui.screens.medications.NavigationRow
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

// TODO: Get medications from firebase DB.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
  modifier: Modifier = Modifier,
  viewModel: DashboardVM = viewModel(),
) {
  val state = viewModel.uiState

  // Get today's date.
  val currentDate = LocalDate.now()
  val formatter = DateTimeFormatter.ofPattern("MMM dd")
  val formattedDate = currentDate.format(formatter)
  Column(
    modifier = modifier
      .fillMaxSize(),
    //.padding(it),
    verticalArrangement = Arrangement.spacedBy(16.dp),

    ) {

    // Screen header with title
             Text(
              "Today, $formattedDate",
              style = MedTrackerTheme.typography.largeTitleEmphasized,
            )

    Spacer(modifier = Modifier.padding(vertical = 8.dp))

    // TODO: Weekly calendar with medication records.

    // Medication Cards List.
    LazyColumn(
      modifier = modifier.fillMaxSize(),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      items(4) {
        DashboardCardComponent(onAddMedicationRecordClick = {/*TODO: */ })
      }

    }
  }
  /*   Scaffold(
      modifier = Modifier.fillMaxSize(),
      containerColor = MedTrackerTheme.colors.primaryBackground,
      topBar = {
        TopAppBar(
          title = { Text("Today, $formattedDate") }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MedTrackerTheme.colors.primaryBackgroundGrouped
          )
        )
      },
      content = {
        Column(
          modifier = modifier
            .fillMaxSize()
            .padding(it),
          verticalArrangement = Arrangement.spacedBy(16.dp),

          ) {

          // Screen header with title
           *//*         Text(
                  "Today, $formattedDate",
                  style = MedTrackerTheme.typography.largeTitleEmphasized,
                ) *//*

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        // TODO: Weekly calendar with medication records.

        // Medication Cards List.
        LazyColumn(
          modifier = modifier.fillMaxSize(),
          verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
          items(4) {
            DashboardCardComponent(onAddMedicationRecordClick = { *//*TODO: *//*  })
          }

        }
      }
    }
  )*/


}

@Composable
fun DashboardCardComponent(
  modifier: Modifier = Modifier,
  title: String = "Medication name",
  dosage: String = "Medication dosage",
  actionLabel: String? = "Add record",
  medicationSchedule: String = "Mon, Tue, Fri...",
  medicationIntakeTime: String = "12:00PM",
  onAddMedicationRecordClick: () -> Unit,
) {

  ElevatedCard(modifier = modifier) {

    Column(
      modifier =
        Modifier.padding(
          horizontal = dimensionResource(R.dimen.card_content_padding_horizontal),
          vertical = dimensionResource(R.dimen.card_content_padding_vertical),
        ),
      verticalArrangement = Arrangement.spacedBy(8.dp)/* spacedBy(dimensionResource(R.dimen.padding_medium)) */,
      horizontalAlignment = Alignment.Start
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

      Row(modifier = Modifier.fillMaxWidth()) {
        // Displays the medication schedule.
        Text(medicationSchedule, style = MedTrackerTheme.typography.body)
        Spacer(modifier = Modifier.weight(1f))
        Text(medicationIntakeTime, style = MedTrackerTheme.typography.body)
      }

      HorizontalDivider(
        color = MedTrackerTheme.colors.opaqueSeparator,
        thickness = 0.5.dp,
        modifier = Modifier.padding(top = 12.dp)
      )

      FlyTextButton(
        onClick = {/*TODO*/ },
        modifier = Modifier.fillMaxWidth(),
        textStyle = MedTrackerTheme.typography.body
      ) {
        Text("Add Record")
      }
    }
  }
}

@Preview(name = "StartScreen")
@Composable
private fun PreviewStartScreen() {
  // StartScreen("empty")
}
