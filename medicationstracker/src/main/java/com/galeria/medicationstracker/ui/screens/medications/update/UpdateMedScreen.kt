package com.galeria.medicationstracker.ui.screens.medications.update

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.ui.components.FlyButton
import com.galeria.medicationstracker.ui.components.MyTextField
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

@Composable
fun UpdateMedScreen(
  modifier: Modifier = Modifier,
  viewModel: UpdateMedVM = viewModel(),
  onConfirmEdit: () -> Unit = {},
  ) {
  val state = viewModel.uiState

  Column(
    modifier = modifier.fillMaxSize(),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {

    // Displays the screen title.
    Text(
      "Edit medication",
      style = MedTrackerTheme.typography.largeTitleEmphasized,
    )
    Spacer(modifier = Modifier.padding(8.dp))

    LazyColumn(
      modifier = modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

      // Name input.
      item {
        Text(
          text = "Name",
          style = MedTrackerTheme.typography.title2,
        )
        MyTextField(
          value = state.medName,
          onValueChange = { viewModel.updateMedName(it) },
          label = "Medication name",
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
          modifier = Modifier.fillMaxWidth(),
        )
      }

      // form.
      item {
        Text(
          text = "Form",
          style = MedTrackerTheme.typography.title2,
        )
        MyTextField(
          value = state.medForm,
          onValueChange = { viewModel.updateMedName(it) },
          label = "Medication form",
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
          modifier = Modifier.fillMaxWidth(),
        )
      }

      // End Date input
      item {
        Text(
          text = "End Date",
          style = MedTrackerTheme.typography.title2,
        )
        MyTextField(
          value = state.endDate,
          onValueChange = { viewModel.updateEndDate(it) },
          label = "End Date",
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
          modifier = Modifier.fillMaxWidth(),
        )
        // Use a suitable date picker or text field for end date input
        // ...
      }
// Start Date input
      item {
        Text(
          text = "Start Date",
          style = MedTrackerTheme.typography.title2,
        )
        MyTextField(
          value = state.startDate,
          onValueChange = { viewModel.updateStartDate(it) },
          label = "Start Date",
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
          modifier = Modifier.fillMaxWidth(),
        )
        // Use a suitable date picker or text field for start date input
        // ...
      }

      // Intake Time input
      item {
        Text(
          text = "Intake Time",
          style = MedTrackerTheme.typography.title2,
        )
        MyTextField(
          value = state.intakeTime,
          onValueChange = { viewModel.updateIntakeTime(it) },
          label = "Intake Time",
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
          modifier = Modifier.fillMaxWidth(),
        )
        // Use a suitable time picker or text field for intake time input
        // ...
      }

      // Notes input
      item {
        Text(
          text = "Notes",
          style = MedTrackerTheme.typography.title2,
        )
        MyTextField(
          value = state.notes, // Assuming you have a medNotes state property
          onValueChange = { viewModel.updateNotes(it) }, // Update the notes state property
          label = "Medication notes",
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
          modifier = Modifier.fillMaxWidth(),
          // maxLines = 4, // Adjust max lines as needed
        )
      }

      // Strength input
      item {
        Text(
          text = "Strength",
          style = MedTrackerTheme.typography.title2,
        )
        MyTextField(
          value = state.strength, // Assuming you have a medStrength state property
          onValueChange = { viewModel.updateStrength(it) }, // Update the strength state property
          label = "Medication strength",
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
          modifier = Modifier.fillMaxWidth(),
        )
        // Add a unit selector or dropdown for strength units (e.g., MG, ML)
        // ...
      }
    }

    FlyButton(
      // TODO: Add editing medication logic.
      onClick = onConfirmEdit
    ) {
      Text("Confirm Editing")
    }
  }


}