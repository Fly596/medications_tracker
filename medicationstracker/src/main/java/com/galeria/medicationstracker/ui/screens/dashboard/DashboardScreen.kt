package com.galeria.medicationstracker.ui.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.data.UserMedication
import com.galeria.medicationstracker.ui.components.FlyCardMedsByTimeList
import com.galeria.medicationstracker.ui.components.FlyTextButton
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

  Column(
    modifier = modifier
      //.fillMaxSize()
      //.padding(it)
      .padding(horizontal = 16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),

    ) {

    Spacer(modifier = Modifier.padding(vertical = 8.dp))

    WeeklyCalendarView()

      // TODO: Replace with medication data from firebase DB.
      val medicationsTest = listOf(
          UserMedication(
              uid = "001",
              name = "Paracetamol",
              form = "Tablet",
              strength = 500f,
              unit = "mg",
              startDate = "2024-12-01",
              endDate = "2024-12-10",
              frequency = "Twice a day",
              intakeTime = "9:25 AM",
              notes = "Take after meals"
          ),

          UserMedication(
              uid = "002",
              name = "Amoxicillin",
              form = "Capsule",
              strength = 250f,
              unit = "mg",
              startDate = "2024-12-05",
              endDate = "2024-12-14",
              frequency = "Three times a day",
              intakeTime = "08:00 AM",
              notes = "Finish the entire course"
          ),

          UserMedication(
              uid = "003",
              name = "Ibuprofen",
              form = "Syrup",
              strength = 100f,
              unit = "mg/5ml",
              startDate = "2024-12-08",
              endDate = "2024-12-15",
              frequency = "Once a day",
              intakeTime = "9:25 AM",
              notes = "Shake well before use"
          ),
      )

      val groupedMeds = medicationsTest.groupBy { it.intakeTime }

    // Medication Cards List.
      FlyCardMedsByTimeList(medications = medicationsTest)

      /*      LazyColumn(
            modifier = modifier.fillMaxSize()  ,
            verticalArrangement = Arrangement.spacedBy(16.dp)
          ) {
            items(16) {


              FlyElevatedCardDashboard(
                title = "Adderall",
                time = "9:00 AM",
                info = "Every Day",
                icon = Icons.Filled.Medication
              )
              // DashboardCardComponent(onAddRecordClick = { })
            }

          }  */
  }


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
