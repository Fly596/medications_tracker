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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDateRangePickerState
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
import com.galeria.medicationstracker.data.MedicationForm
import com.galeria.medicationstracker.data.MedicationUnit
import com.galeria.medicationstracker.ui.components.HIGButton
import com.galeria.medicationstracker.ui.components.MyTextField
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme
import java.util.Date
import java.util.Locale

@Composable
fun NewMedicationDataScreen(
  onConfirmClick: () -> Unit,
  viewModel: AddNewMedViewModel = viewModel(),
) {
  val state = viewModel.uiState

  Column(modifier = Modifier.fillMaxSize().padding(top = 32.dp)) {
    Text(stringResource(R.string.enter_med_values), style = MedTrackerTheme.typography.title1)

    Spacer(modifier = Modifier.padding(8.dp))

    MyTextField(
      value = state.name,
      onValueChange = { viewModel.updateMedName(it) },
      label = "Medication name",
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
      modifier = Modifier.fillMaxWidth(),
    )

    Spacer(modifier = Modifier.padding(8.dp))

    // Form.
    Text(
      stringResource(R.string.add_new_med_form_screen_title),
      style = MedTrackerTheme.typography.title2,
    )
    var selectedForm by remember { mutableStateOf(state.form) }
    val options = MedicationForm.entries.toTypedArray()
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
      options.forEach { form ->
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(text = form.toString())
          RadioButton(selected = selectedForm == form, onClick = { selectedForm = form })
        }
      }
    }

    Spacer(modifier = Modifier.padding(8.dp))

    // Strength.
    Text(
      stringResource(R.string.add_new_med_strength_screen_title),
      style = MedTrackerTheme.typography.title2,
    )
    Spacer(modifier = Modifier.padding(8.dp))

    var selectedUnit by remember { mutableStateOf(state.unit) }
    val unitOptions = MedicationUnit.entries.toTypedArray()
    /*     MyTextField(
      value = state.strength,
      onValueChange = { viewModel.updateMedStrength(it) },
      label = "Medication Strength",
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
      modifier = Modifier.fillMaxWidth(),
    ) */

    Spacer(modifier = Modifier.padding(8.dp))

    // Units.
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
      unitOptions.forEach { unit ->
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(text = unit.toString())
          RadioButton(selected = selectedUnit == unit, onClick = { selectedUnit = unit })
        }
      }
    }
    Spacer(modifier = Modifier.padding(8.dp))

    ModalDatePicker(state, viewModel)

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
    val context = LocalContext.current
    HIGButton(
      text = "Confirm",
      onClick = {
        viewModel.addMedication(context)
        onConfirmClick.invoke()
      },
      modifier = Modifier.fillMaxWidth(),
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalDatePicker(state: NewMedicationUiState, viewModel: AddNewMedViewModel) {

  var showPicker by remember { mutableStateOf(false) }
  HIGButton(text = "Select start and end dates", onClick = { showPicker = !showPicker })

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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerModal(onDateRangeSelected: (Pair<Long?, Long?>) -> Unit, onDismiss: () -> Unit) {
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
      modifier = Modifier.fillMaxWidth().height(500.dp).padding(16.dp),
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
  datePickerState: DatePickerState,
  onDateSelected: (Long?) -> Unit,
  onDismiss: () -> Unit,
) {
  DatePickerDialog(
    onDismissRequest = onDismiss,
    confirmButton = {
      TextButton(
        onClick = {
          onDateSelected(datePickerState.selectedDateMillis)
          onDismiss()
        }
      ) {
        Text("OK")
      }
    },
    dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } },
  ) {
    DatePicker(state = datePickerState)
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
fun NewMedNameScreen(onNextClick: () -> Unit, viewModel: AddNewMedViewModel = viewModel()) {
  val uid = viewModel.userId

  val state = viewModel.uiState

  Column(modifier = Modifier.fillMaxSize().padding(top = 32.dp)) {
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
fun NewMedFormScreen(viewModel: AddNewMedViewModel = viewModel(), onNextClick: () -> Unit) {
  var state = viewModel.uiState

  var selectedForm by remember { mutableStateOf(state.form) }
  val options = MedicationForm.entries.toTypedArray()

  Column(modifier = Modifier.fillMaxSize().padding(top = 32.dp)) {

    // Header.
    Text(
      stringResource(R.string.add_new_med_form_screen_title),
      style = MedTrackerTheme.typography.largeTitle,
    )

    Spacer(modifier = Modifier.padding(8.dp))

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
      options.forEach { form ->
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(text = form.toString())
          RadioButton(selected = selectedForm == form, onClick = { selectedForm = form })
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
fun NewMedStrengthScreen(viewModel: AddNewMedViewModel = viewModel(), onNextClick: () -> Unit) {
  val state = viewModel.uiState
  var selectedUnit by remember { mutableStateOf(state.unit) }
  val unitOptions = MedicationUnit.entries.toTypedArray()

  Column(modifier = Modifier.fillMaxSize().padding(top = 32.dp)) {

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
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
      unitOptions.forEach { unit ->
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(text = unit.toString())
          RadioButton(selected = selectedUnit == unit, onClick = { selectedUnit = unit })
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
fun NewMedFrequencyScreen(viewModel: AddNewMedViewModel = viewModel(), onNextClick: () -> Unit) {
  val state = viewModel.uiState
  var selectedFrequency by remember { mutableStateOf(state.frequency) }
  val frequencyOptions = listOf(Frequency.OnSpecificDaysOfTheWeek(), Frequency.AtRegularIntervals())

  // Header.
  Text(
    stringResource(R.string.add_new_med_frequency_screen_title),
    style = MedTrackerTheme.typography.largeTitle,
  )

  Spacer(modifier = Modifier.padding(8.dp))

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

  HIGButton(text = "Next", onClick = { onNextClick.invoke() }, modifier = Modifier.fillMaxWidth())
}

@Composable
fun NewMedReminderScreen(viewModel: AddNewMedViewModel = viewModel(), onNextClick: () -> Unit) {}

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
