package com.galeria.medicationstracker.ui.screens.profile.appoinment

import android.icu.text.*
import android.icu.util.Calendar
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import androidx.lifecycle.compose.*
import com.galeria.medicationstracker.ui.componentsOld.*
import com.galeria.medicationstracker.ui.screens.medications.newmed.convertMillisToDate
import com.galeria.medicationstracker.ui.screens.profile.*
import com.galeria.medicationstracker.ui.theme.*
import com.galeria.medicationstracker.utils.*
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileVM,
    onBackClick: () -> Unit = {}
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    var selectedDate by remember { mutableStateOf<Long?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Schedule Your Appointment",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        FlySimpleCard(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Date:",
                    style = MedTrackerTheme.typography.body,
                )
                Spacer(modifier = Modifier.height(8.dp))

                if (selectedDate != null) {
                    val date = Date(selectedDate!!)
                    val formattedDate =
                        SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(date)
                    Text(
                        formattedDate,
                        style = MedTrackerTheme.typography.title3Emphasized,
                    )
                } else {
                    Text("No date selected")
                }
                var showDatePicker by remember { mutableStateOf(false) }

                FlyTonalButton(
                    onClick = { showDatePicker = true },
                    shape = MaterialTheme.shapes.small
                ) {
                    Text("Select Date")
                }
                if (showDatePicker) {
                    DatePickerModalInput(
                        onDateSelected = {
                            selectedDate = it
                            viewModel.updateSelectedDate(
                                parseDateForFirestore(
                                    convertMillisToDate(
                                        selectedDate
                                    )
                                )
                            )
                            showDatePicker = false
                        },
                        onDismiss = {
                            showDatePicker = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        FlySimpleCard(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                // Display selected date
                var showTimePicker by remember { mutableStateOf(false) }
                var selectedTimeState: TimePickerState? by remember { mutableStateOf(null) }
                val timeFormatter = remember { SimpleDateFormat("hh:mm a", Locale.getDefault()) }
                Text(
                    text = "Time:",
                    style = MedTrackerTheme.typography.body,
                )
                Spacer(modifier = Modifier.height(8.dp))


                if (showTimePicker) {
                    InputExample(
                        onDismiss = {
                            showTimePicker = false
                        },
                        onConfirm = { time ->
                            viewModel.updateSelectedTime("${time.hour}:${time.minute}")
                            selectedTimeState = time
                            showTimePicker = false
                        }
                    )
                }


                if (selectedTimeState != null) {
                    val cal = Calendar.getInstance()

                    cal.set(Calendar.HOUR_OF_DAY, selectedTimeState!!.hour)
                    cal.set(Calendar.MINUTE, selectedTimeState!!.minute)
                    cal.isLenient = false
                    Text(
                        timeFormatter.format(cal.time),
                        style = MedTrackerTheme.typography.title3Emphasized,
                    )
                } else {
                    Text(
                        "No time selected.",
                        style = MedTrackerTheme.typography.body,
                    )
                }
                FlyTonalButton(
                    onClick = {
                        showTimePicker = true
                    },
                    shape = MaterialTheme.shapes.small
                ) {
                    Text("Select Time")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FlyButton(
                onClick = {
                    viewModel.addAppointment()
                    onBackClick.invoke()
                },
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = "Confirm Appointment",
                )
            }
            FlyTextButton(
                onClick = {
                    /* TODO: Add logic to cancel appointment */
                },
            ) {
                Text(
                    text = "Cancel",
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModalInput(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputExample(
    onConfirm: (TimePickerState) -> Unit,
    onDismiss: () -> Unit,
) {
    val currentTime = Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    Column {
        TimeInput(
            state = timePickerState,
        )
        Button(onClick = onDismiss) {
            Text("Dismiss picker")
        }
        Button(onClick = {
            onConfirm(timePickerState)
        }) {
            Text("Confirm selection")
        }
    }
}
