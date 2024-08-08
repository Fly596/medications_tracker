package com.galeria.medicationstracker.ui.screens.new_medication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.galeria.medicationstracker.data.UserMedication
import com.galeria.medicationstracker.ui.screens.autentification.login.MyTextField
import com.galeria.medicationstracker.ui.shared.components.HIGButton
import com.galeria.medicationstracker.ui.theme.MedicationsTrackerAppTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun NewMedicationScreen(
    viewModel: NewMedicationViewModel = NewMedicationViewModel(),
    modifier: Modifier = Modifier
) {
    val inputName by viewModel.inputName.collectAsState()
    val inputForm by viewModel.inputForm.collectAsState()
    val inputStrength by viewModel.inputStrength.collectAsState()
    val inputUnit by viewModel.inputUnit.collectAsState()
    val inputFrequency by viewModel.inputFrequency.collectAsState()
    val inputTime by viewModel.inputTime.collectAsState()
    val inputNotes by viewModel.inputNotes.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarState by viewModel.snackbarState.collectAsState()

    var newMed: UserMedication

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            MyTextField(
                value = inputName,
                onValueChange = { viewModel.onInputNameChange(it) },
                label = "Name",
                modifier = Modifier.fillMaxWidth(),
            )
            MyTextField(
                value = inputForm,
                onValueChange = { viewModel.onInputFormChange(it) },
                label = "Form",
                modifier = Modifier.fillMaxWidth(),
            )

            MyTextField(
                value = inputStrength,
                onValueChange = { viewModel.onInputStrengthChange(it) },
                label = "Strength",
                modifier = Modifier.fillMaxWidth(),
            )

            MyTextField(
                value = inputUnit,
                onValueChange = { viewModel.onInputUnitChange(it) },
                label = "Unit",
                modifier = Modifier.fillMaxWidth(),
            )
            MyTextField(
                value = inputFrequency,
                onValueChange = { viewModel.onInputFrequencyChange(it) },
                label = "Frequency",
                modifier = Modifier.fillMaxWidth(),
            )

/*             MyTextField(
                value = inputTime,
                onValueChange = { viewModel.onInputTimeChange(it) },
                label = "Time",
                modifier = Modifier.fillMaxWidth(),
            ) */

            MyTextField(
                value = inputNotes,
                onValueChange = { viewModel.onInputNotesChange(it) },
                label = "Notes",
                modifier = Modifier.fillMaxWidth(),
                singleLine = false
            )

            InputExample(

            )

            HIGButton(
                text = "Add medication",
                onClick = {
                    newMed = UserMedication(
                        name = inputName,
                        form = inputForm,
                        strength = inputStrength.toFloat(),
                        unit = inputUnit,
                        frequency = inputFrequency,
                         notes = inputNotes
                    )
                    viewModel.addMedication(
                        newMed
                    )
                },
            )

        }
        LaunchedEffect(snackbarState.message) {
            snackbarState.message?.let { message ->
                snackbarHostState.showSnackbar(message)
            }
        }
    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputExample(
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = false,
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimeInput(
            state = timePickerState,
        )
/*         HIGButton(
            text = "Confirm",
            onClick = onConfirm
        ) */
    }
}

@Preview(
    showBackground = false,
    showSystemUi = true,
    device = "spec:parent=pixel_8,navigation=buttons"
)
@Composable
private fun PreviewNewMed() {
   // val viewModel: NewMedicationViewModel = NewMedicationViewModel()
    MedicationsTrackerAppTheme {
            NewMedicationScreen()
    }
}


// region Timepicker
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialExample(
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimePicker(
            state = timePickerState,
        )
        Button(onClick = onDismiss) {
            Text("Dismiss picker")
        }
        Button(onClick = onConfirm) {
            Text("Confirm selection")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialUseStateExample(
    onConfirm: (TimePickerState) -> Unit = {},
    onDismiss: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val currentTime = Calendar.getInstance()
    var selectedTime: TimePickerState? by remember { mutableStateOf(null) }

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = false,
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimePicker(
            state = timePickerState,
        )
        Button(onClick = onDismiss) {
            Text("Dismiss picker")
        }
        Button(
            onClick = {
                selectedTime = timePickerState
                // onConfirm(timePickerState)
            }) {
            Text("Confirm selection")
        }
    }
}

// endregion


// region DatePicker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDocked(
    modifier: Modifier = Modifier
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedDate,
            onValueChange = { },
            label = { Text("DOB") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = !showDatePicker }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select date"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        )

        if (showDatePicker) {
            Popup(
                onDismissRequest = { showDatePicker = false },
                alignment = Alignment.TopStart
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = 64.dp)
                        .shadow(elevation = 4.dp)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                ) {
                    DatePicker(
                        state = datePickerState,
                        showModeToggle = false
                    )
                }
            }
        }
    }
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

// endregion