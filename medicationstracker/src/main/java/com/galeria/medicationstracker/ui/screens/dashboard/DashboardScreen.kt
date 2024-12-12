package com.galeria.medicationstracker.ui.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.ui.components.FlyElevatedCardDashboard
import com.galeria.medicationstracker.ui.components.FlyTextButton
import com.galeria.medicationstracker.ui.components.FlyTopAppBar
import com.galeria.medicationstracker.ui.components.NavigationRow
import com.galeria.medicationstracker.ui.components.WeeklyCalendarView
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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

  Scaffold(
    modifier = Modifier,
    topBar = {
      FlyTopAppBar(
        "Today, $formattedCurrentDate",
        onClick = onLogsClick,
        icon = Icons.Filled.History
      )
      // FlyTopAppBar("Today, $formattedCurrentDate")
    },
    containerColor = MedTrackerTheme.colors.secondaryBackground,
    content = {
      Column(
        modifier = modifier
          .fillMaxSize()
          .padding(it)
          .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),

        ) {

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        WeeklyCalendarView()

        // TODO: Weekly calendar with medication records.

        // Medication Cards List.
        LazyColumn(
          modifier = modifier.fillMaxSize(),
          verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
          items(5) {
            FlyElevatedCardDashboard(
              title = "Adderall",
              time = "9:00 AM",
              info = "Every Day",
              icon = Icons.Filled.Medication
            )
            // DashboardCardComponent(onAddRecordClick = {/*TODO: */ })
          }

        }
      }
    }

  )
}

@Composable
fun DashboardCardComponent(
  modifier: Modifier = Modifier,
  title: String = "Medication name",
  dosage: String = "Medication dosage",
  actionLabel: String? = "Add record",
  schedule: String = "Mon, Tue, Fri...",
  intakeTime: String = "12:00PM",
  onAddRecordClick: () -> Unit,
) {

  ElevatedCard(
    modifier = modifier,
    colors = CardDefaults.elevatedCardColors(
      containerColor = MedTrackerTheme.colors.primaryBackground,
      contentColor = MedTrackerTheme.colors.primaryLabel
    )
  ) {

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
        NavigationRow({ onAddRecordClick() }, actionLabel ?: "")
      }

      // Displays the medication dosage.
      Text(dosage, style = MedTrackerTheme.typography.subhead)

      Row(modifier = Modifier.fillMaxWidth()) {
        // Displays the medication schedule.
        Text(schedule, style = MedTrackerTheme.typography.body)
        Spacer(modifier = Modifier.weight(1f))
        Text(intakeTime, style = MedTrackerTheme.typography.body)
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
