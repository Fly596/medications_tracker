package com.galeria.medicationstracker.ui.screens.medications

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.data.MedicationForms
import com.galeria.medicationstracker.data.MedicationUnit
import com.galeria.medicationstracker.ui.components.DayOfWeekSelector
import com.galeria.medicationstracker.ui.components.FlyButton
import com.galeria.medicationstracker.ui.components.FlySimpleCard
import com.galeria.medicationstracker.ui.components.FlyTonalButton
import com.galeria.medicationstracker.ui.components.HIGButton
import com.galeria.medicationstracker.ui.components.MyRadioButton
import com.galeria.medicationstracker.ui.components.MyTextField
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme.colors
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewMedicationDataScreen(
  onConfirmClick: () -> Unit,
  viewModel: AddNewMedViewModel = viewModel(),
) {
  val state = viewModel.uiState

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      //.padding(it)
      .padding(horizontal = 16.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {

    item {
      Spacer(modifier = Modifier.padding(8.dp))

      FlySimpleCard(
        content = {
          Text(
            stringResource(R.string.add_new_med_name_screen_title),
            style = MedTrackerTheme.typography.title2,
          )

          // Spacer(modifier = Modifier.padding(8.dp))

          // Name input.
          MyTextField(
            value = state.medName,
            onValueChange = { viewModel.updateMedName(it) },
            label = "Add Medication name",
            isPrimaryColor = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
              .fillMaxWidth()
            // .padding(vertical = 8.dp),
          )
        }
      )

    }

    // Form.
    item {
      var selectedForm by remember { mutableStateOf(state.medForm) }
      val options = MedicationForms.entries.toTypedArray()

      FlySimpleCard(
        content = {
          Text(
            stringResource(R.string.add_new_med_form_screen_title),
            style = MedTrackerTheme.typography.title2,
          )

          // Spacer(modifier = Modifier.padding(8.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            options.forEach { form ->
              Column(verticalArrangement = Arrangement.Center) {
                MyRadioButton(
                  selected = selectedForm == form,
                  onClick = { selectedForm = form },
                  caption = form.toString().lowercase()
                )
                // Text(text = form.toString().lowercase())

              }
            }
          }
        }

      )

    }

    // Strength.
    item {
      var selectedUnit by remember { mutableStateOf(state.medUnit) }
      val unitOptions = MedicationUnit.entries.toTypedArray()

      FlySimpleCard(
        content = {
          Text(
            stringResource(R.string.add_new_med_strength_screen_title),
            style = MedTrackerTheme.typography.title2,
          )
          // Spacer(modifier = Modifier.padding(8.dp))

          MyTextField(
            value = state.medStrength.toString(),
            onValueChange = { viewModel.updateMedStrength(it) },
            label = "Medication Strength",
            isPrimaryColor = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
          )

          // Units.
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            unitOptions.forEach { unit ->
              Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = unit.toString().lowercase())
                MyRadioButton(
                  selected = selectedUnit == unit,
                  onClick = {
                    viewModel.updateMedUnit(selectedUnit)
                    selectedUnit = unit
                  }
                )
              }
            }
          }

        }
      )

      // Spacer(modifier = Modifier.padding(8.dp))
    }

    // Start and end dates + time.
    item {
      ModalDatePicker(state, viewModel)

      var showTimePicker by remember { mutableStateOf(false) }

      FlyButton(
        onClick = { showTimePicker = true }) {
        Text("Set time")
      }

      if (showTimePicker) {
        IntakeTimePicker(
          onConfirm = {
            showTimePicker = false
          },
          onDismiss = { showTimePicker = false },
          viewModel
        )
      }
    }

    // days.
    item {

      FlySimpleCard(
        content = {

          Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
          ) {
            Text(
              text = "Choose Days",
              style = MedTrackerTheme.typography.title2Emphasized,
            )
            DayOfWeekSelector(
              viewModel = viewModel
            )
          }
        }
      )
    }

    // button to add medication.
    item {
      val context = LocalContext.current
      HIGButton(
        text = "Add Medication",
        onClick = {
          viewModel.addMedication(context)
          onConfirmClick.invoke()
        },
        modifier = Modifier.fillMaxWidth(),
      )
    }

  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalDatePicker(
  state: NewMedicationUiState,
  viewModel: AddNewMedViewModel
) {
  var showPicker by remember { mutableStateOf(false) }

  Row(
    horizontalArrangement = Arrangement.spacedBy(16.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {

    FlyButton(
      onClick = { showPicker = !showPicker }
    ) {
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

    MyTextField(
      value = "",
      label = "Start: ${viewModel.uiState.medStartDate}   End: ${viewModel.uiState.medEndDate}",
      onValueChange = {},
      readOnly = true,
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerModal(
  onDateRangeSelected: (Pair<Long?, Long?>) -> Unit,
  onDismiss: () -> Unit
) {
  val dateRangePickerState = rememberDateRangePickerState()

  DatePickerDialog(
    onDismissRequest = onDismiss,
    confirmButton = {
      TextButton(
        onClick = {
          onDateRangeSelected(
            Pair(
              dateRangePickerState.selectedStartDateMillis,
              dateRangePickerState.selectedEndDateMillis,
            )
          )
        }
      ) {
        Text("OK")
      }
    },
    dismissButton = { TextButton(onClick = { onDismiss.invoke() }) { Text("Cancel") } },
  ) {
    DateRangePicker(
      state = dateRangePickerState,
      title = { Text(text = "Select date range") },
      showModeToggle = false,
      modifier = Modifier
        .fillMaxWidth()
        .height(500.dp)
        .padding(16.dp),
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntakeTimePicker(
  onConfirm: () -> Unit,
  onDismiss: () -> Unit,
  viewModel: AddNewMedViewModel
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
      colors = TimePickerDefaults.colors(
        clockDialColor = colors.tertiaryBackground,
        clockDialSelectedContentColor = colors.primaryLabel,
        clockDialUnselectedContentColor = colors.primaryLabel,
        selectorColor = colors.primary400,
        periodSelectorBorderColor = colors.opaqueSeparator,
        periodSelectorSelectedContentColor = colors.primaryLabel,
        periodSelectorUnselectedContentColor = colors.primaryLabel,
        periodSelectorSelectedContainerColor = colors.primaryTinted,
        timeSelectorSelectedContainerColor = colors.primaryTinted,
        timeSelectorUnselectedContainerColor = colors.primaryLight
      )
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

fun convertMillisToDate(millis: Long?): String {
  val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())

  if (millis != null) {
    return formatter.format(Date(millis))
  }

  return ""
}