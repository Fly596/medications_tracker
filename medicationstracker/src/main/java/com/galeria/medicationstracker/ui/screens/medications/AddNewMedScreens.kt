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
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TimePicker
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
import com.galeria.medicationstracker.data.Frequency
import com.galeria.medicationstracker.data.MedicationForms
import com.galeria.medicationstracker.data.MedicationUnit
import com.galeria.medicationstracker.ui.components.FlyButton
import com.galeria.medicationstracker.ui.components.FlyTonalButton
import com.galeria.medicationstracker.ui.components.HIGButton
import com.galeria.medicationstracker.ui.components.MyTextField
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale


@Composable
fun NewMedicationDataScreen(
  onConfirmClick: () -> Unit,
  viewModel: AddNewMedViewModel = viewModel(),
) {
  val state = viewModel.uiState

  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    item {
      Text(
        stringResource(R.string.enter_med_values),
        style = MedTrackerTheme.typography.title1Emphasized
      )
      Spacer(modifier = Modifier.padding(8.dp))

    }

    item {

      // Name input.
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

      // Spacer(modifier = Modifier.padding(8.dp))
    }

    // Form.
    item {
      var selectedForm by remember { mutableStateOf(state.medForm) }
      val options = MedicationForms.entries.toTypedArray()

      Text(
        stringResource(R.string.add_new_med_form_screen_title),
        style = MedTrackerTheme.typography.title2,
      )

      Spacer(modifier = Modifier.padding(8.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        options.forEach { form ->
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = form.toString().lowercase())
            RadioButton(
              selected = selectedForm == form,
              onClick = { selectedForm = form })
          }
        }
      }
    }

    // Strength.
    item {
      Text(
        stringResource(R.string.add_new_med_strength_screen_title),
        style = MedTrackerTheme.typography.title2,
      )
      Spacer(modifier = Modifier.padding(8.dp))

      var selectedUnit by remember { mutableStateOf(state.medUnit) }
      val unitOptions = MedicationUnit.entries.toTypedArray()
      MyTextField(
        value = state.medStrength.toString(),
        onValueChange = { viewModel.updateMedStrength(it) },
        label = "Medication Strength",
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
            RadioButton(
              selected = selectedUnit == unit,
              onClick = { selectedUnit = unit })
          }
        }
      }
      // Spacer(modifier = Modifier.padding(8.dp))
    }


    item {
      ModalDatePicker(state, viewModel)
    }

    item {
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
    HIGButton(
      text = "Select start and end dates",
      onClick = { showPicker = !showPicker })

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
      value = "Start: ${viewModel.uiState.medStartDate}   End: ${viewModel.uiState.medEndDate}",
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
    )
    FlyButton(onClick = {
      viewModel.updateIntakeTime(time.format(dtf))
      onDismiss;
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

// region later
@Composable
fun NewMedNameScreen(
  onNextClick: () -> Unit,
  viewModel: AddNewMedViewModel = viewModel()
) {
  val uid = viewModel.userId

  val state = viewModel.uiState

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(top = 32.dp)
  ) {
    Text(
      stringResource(R.string.add_new_med_name_screen_title),
      style = MedTrackerTheme.typography.largeTitle,
    )

    Spacer(modifier = Modifier.padding(8.dp))

    /*     MyTextField(
      value = state.name,
      onValueChange = { viewModel.updateMedName(it) },
      label = "Medication name",
      modifier = Modifier.fillMaxWidth(),
    ) */

    Spacer(modifier = Modifier.padding(8.dp))

    val context = LocalContext.current
    HIGButton(
      text = "Next",
      onClick = {
        onNextClick.invoke()
        // val newMed = TEMP_Medication(uid = uid, name = state.name, type = state.type)
        // viewModel.addMedication(newMed, context)
      },
      modifier = Modifier.fillMaxWidth(),
    )
  }
}

@Composable
fun NewMedFormScreen(
  viewModel: AddNewMedViewModel = viewModel(),
  onNextClick: () -> Unit
) {
  var state = viewModel.uiState

  var selectedForm by remember { mutableStateOf(state.medForm) }
  val options = MedicationForms.entries.toTypedArray()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(top = 32.dp)
  ) {

    // Header.
    Text(
      stringResource(R.string.add_new_med_form_screen_title),
      style = MedTrackerTheme.typography.largeTitle,
    )

    Spacer(modifier = Modifier.padding(8.dp))

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      options.forEach { form ->
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(text = form.toString())
          RadioButton(
            selected = selectedForm == form,
            onClick = { selectedForm = form })
        }
      }
    }

    Spacer(modifier = Modifier.padding(8.dp))

    HIGButton(
      text = "Next",
      onClick = {
        viewModel.updateMedForm(selectedForm)
        onNextClick.invoke()
      },
      modifier = Modifier.fillMaxWidth(),
    )
  }
}

@Composable
fun NewMedStrengthScreen(
  viewModel: AddNewMedViewModel = viewModel(),
  onNextClick: () -> Unit
) {
  val state = viewModel.uiState
  var selectedUnit by remember { mutableStateOf(state.medUnit) }
  val unitOptions = MedicationUnit.entries.toTypedArray()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(top = 32.dp)
  ) {

    // Header.
    Text(
      stringResource(R.string.add_new_med_strength_screen_title),
      style = MedTrackerTheme.typography.largeTitle,
    )

    Spacer(modifier = Modifier.padding(8.dp))

    /*     MyTextField(
      value = state.strength.toString(),
      onValueChange = { viewModel.updateMedStrength(it) },
      label = "Medication Strength",
      modifier = Modifier.fillMaxWidth(),
    ) */

    Spacer(modifier = Modifier.padding(8.dp))

    // Units.
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      unitOptions.forEach { unit ->
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(text = unit.toString())
          RadioButton(
            selected = selectedUnit == unit,
            onClick = { selectedUnit = unit })
        }
      }
    }

    Spacer(modifier = Modifier.padding(8.dp))

    HIGButton(
      text = "Next",
      onClick = {
        viewModel.updateMedUnit(selectedUnit)
        onNextClick.invoke()
      },
      modifier = Modifier.fillMaxWidth(),
    )
  }
}

@Composable
fun NewMedFrequencyScreen(
  viewModel: AddNewMedViewModel = viewModel(),
  onNextClick: () -> Unit
) {
  val state = viewModel.uiState
  // var selectedFrequency by remember { mutableStateOf(state.frequency) }
  val frequencyOptions =
    listOf(Frequency.OnSpecificDaysOfTheWeek(), Frequency.AtRegularIntervals())

  // Header.
  Text(
    stringResource(R.string.add_new_med_frequency_screen_title),
    style = MedTrackerTheme.typography.largeTitle,
  )

  Spacer(modifier = Modifier.padding(8.dp))

  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    frequencyOptions.forEach { option ->
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = option.toString())
        /*         RadioButton(
                  selected = selectedFrequency == option,
                  onClick = { selectedFrequency = option },
                ) */
      }
    }
  }

  /*var selectedState by remember { mutableStateOf(true) }
  // Frequency choice.
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .selectableGroup(),
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Text(text = "At Regular Intervals")
      RadioButton(
        selected = selectedFrequency == frequencyOptions[0],
        onClick = {
          selectedState = true
          viewModel.updateMedFrequency(newFrequency = Frequency.AtRegularIntervals())
        }
      )
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
      Text(text = "On Specific Days Of The Week")
      RadioButton(
        selected = selectedFrequency == frequencyOptions[1],
        onClick = {
          selectedState = false
          viewModel.updateMedFrequency(newFrequency = Frequency.OnSpecificDaysOfTheWeek())
        })
    }
  }*/

  Spacer(modifier = Modifier.padding(8.dp))

  HIGButton(
    text = "Next",
    onClick = { onNextClick.invoke() },
    modifier = Modifier.fillMaxWidth()
  )
}

@Composable
fun NewMedReminderScreen(
  viewModel: AddNewMedViewModel = viewModel(),
  onNextClick: () -> Unit
) {
}

// endregion

/* MyTextField(
  value = state.type,
  onValueChange = { viewModel.updateMedType(it) },
  label = "Medication type",
  modifier = Modifier.fillMaxWidth(),
)
MyTextField(
  value = if (state.strength != 0.0f) state.strength.toString() else "",
  onValueChange = { viewModel.updateMedStrength(it.toFloat()) },
  label = "Strength",
  modifier = Modifier.fillMaxWidth(),
)
MyTextField(
  value = state.notes,
  onValueChange = { viewModel.updateMedNotes(it) },
  label = "Notes",
  modifier = Modifier.fillMaxWidth(),
) */

/* TODO: Date Picker.
 HIGButton(
  text = "Start Date",
  onClick = { viewModel.showDatePicker = !viewModel.showDatePicker },
  enabled = true,
)
ModalDatePicker(viewModel) */

// Frequency.
/*     var selectedFrequency by remember { mutableStateOf(state.frequency) }
val frequencyOptions =
  listOf(Frequency.OnSpecificDaysOfTheWeek(), Frequency.AtRegularIntervals())

Text(
  stringResource(R.string.add_new_med_frequency_screen_title),
  style = MedTrackerTheme.typography.title2,
)
Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
  frequencyOptions.forEach { option ->
    Row(verticalAlignment = Alignment.CenterVertically) {
      Text(text = option.toString())
      RadioButton(
        selected = selectedFrequency == option,
        onClick = { selectedFrequency = option },
      )
    }
  }
} */


// if (
/* viewModel.selectedStartDate != null &&
viewModel.selectedEndDate != null && */
// state.showDatePicker
// ) {
/*     DateRangePickerModal(
  onDateRangeSelected = {
    selectedDate = it.first.toString()
    selectEndDate = it.second.toString()
  },
  onDismiss = { viewModel.isShowDateChecked(false) },
) */

/*  val dateS = Date(selectedDate!!)
val dateE = Date(selectEndDate!!)
val formatedDateS = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(dateS)
val formatedDateE = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(dateE)
Text("Selected date: $formatedDateS to $formatedDateE") */
// } else {
// Text("No data selected.")

// val dateS = Date(selectedDate!!)
// val dateE = Date(selectEndDate!!)
// val formatedDateS = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(dateS)
// val formatedDateE = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(dateE)

// Text("Selected date: $formatedDateS to $formatedDateE")
// }