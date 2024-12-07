package com.galeria.medicationstracker.ui.screens.medications.update

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.ui.components.FlyButton
import com.galeria.medicationstracker.ui.components.FlyErrorButton
import com.galeria.medicationstracker.ui.components.FlyTonalButton
import com.galeria.medicationstracker.ui.components.MyTextField
import com.galeria.medicationstracker.ui.screens.medications.DateRangePickerModal
import com.galeria.medicationstracker.ui.screens.medications.convertMillisToDate
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar


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
        Spacer(modifier = Modifier.padding(4.dp))
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
        Spacer(modifier = Modifier.padding(4.dp))

        MyTextField(
          value = state.medForm,
          onValueChange = { viewModel.updateMedForm(it) },
          label = "Medication form",
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
          modifier = Modifier.fillMaxWidth(),
        )
      }

      // End Date input
      item {
        DatePicker(viewModel)

      }
// Start Date input

      // Intake Time input
      item {
        var showTimePicker by remember { mutableStateOf(false) }

        FlyButton(
          onClick = { showTimePicker = true }) {
          Text("Set time")
        }

        if (showTimePicker) {
          NewIntakeTimePicker(
            onConfirm = {
              showTimePicker = false
            },
            onDismiss = { showTimePicker = false },
            viewModel
          )
        }

      }

      // Notes input
      item {
        Text(
          text = "Notes",
          style = MedTrackerTheme.typography.title2,
        )
        Spacer(modifier = Modifier.padding(4.dp))

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
        Spacer(modifier = Modifier.padding(4.dp))

        MyTextField(
          value = state.strength, // Assuming you have a medStrength state property
          onValueChange = { viewModel.updateStrength(it) }, // Update the strength state property
          label = "Medication strength",
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
          modifier = Modifier.fillMaxWidth(),
        )
        // Add a unit selector or dropdown for strength units (e.g., MG, ML)
        // ...
      }
    }

    Row(modifier  = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
      FlyButton(
        // TODO: Add editing medication logic.
        onClick = onConfirmEdit
      ) {
        Text("Confirm")
      }

      FlyErrorButton(
        // TODO: Add editing medication logic.
        onClick = onConfirmEdit
      ) {
        Text("Delete Medication")
      }
    }

  }
}

@Composable
fun DatePicker(
  viewModel: UpdateMedVM
) {
  var showPicker by remember { mutableStateOf(false) }
  Row(
    horizontalArrangement = Arrangement.spacedBy(16.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    FlyButton(
      onClick = { showPicker = !showPicker }) {
      Text(text = "Select start and end dates")
    }

    if (showPicker) {
      DateRangePickerModal(
        onDateRangeSelected = {
          viewModel.updateStartDate(convertMillisToDate(it.first))
          viewModel.updateEndDate(convertMillisToDate(it.second))
          showPicker = !showPicker
        },
        onDismiss = { showPicker = !showPicker },
      )
    }
    TextField(
      value = "Start: ${viewModel.uiState.startDate}   End: ${viewModel.uiState.endDate}",
      onValueChange = {},
      readOnly = true,
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewIntakeTimePicker(
  onConfirm: () -> Unit,
  onDismiss: () -> Unit,
  viewModel: UpdateMedVM
) {
  val currentTime = Calendar.getInstance()

  val timePickerState = rememberTimePickerState(
    initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
    initialMinute = currentTime.get(Calendar.MINUTE),
    is24Hour = false,
  )

  val time = LocalTime.of(timePickerState.hour, timePickerState.minute)
  val dtf = DateTimeFormatter.ofPattern("HH:mm")

  Column(horizontalAlignment = Alignment.CenterHorizontally) {
    TimePicker(
      state = timePickerState,
    )
    FlyButton(onClick = {
      viewModel.updateIntakeTime(time.format(dtf))
      onDismiss.invoke();
    }) {
      Text("Confirm Time")
    }
    FlyTonalButton(onClick = onDismiss) {
      Text("Dismiss")
    }

  }
}