package com.galeria.medicationstracker.ui.screens.medications.newmed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.data.MedicationForm
import com.galeria.medicationstracker.data.MedicationUnit
import com.galeria.medicationstracker.ui.components.DropdownMenuExample
import com.galeria.medicationstracker.ui.components.GOutlinedButton
import com.galeria.medicationstracker.ui.components.GPrimaryButton
import com.galeria.medicationstracker.ui.components.GRadioButton
import com.galeria.medicationstracker.ui.components.GSecondaryButton
import com.galeria.medicationstracker.ui.components.GTextField
import com.galeria.medicationstracker.ui.componentsOld.DayOfWeekSelector
import com.galeria.medicationstracker.ui.componentsOld.FlySimpleCard
import com.galeria.medicationstracker.ui.componentsOld.HeaderWithIconButtonAndTitle
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme
import com.galeria.medicationstracker.utils.formatTimestampTillTheDay
import com.galeria.medicationstracker.utils.parseDateForFirestore
import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewMedicationDataScreen(
    onConfirmClick: () -> Unit,
    viewModel: AddNewMedViewModel = hiltViewModel(),
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        HeaderWithIconButtonAndTitle(
            onBackClick = { onConfirmClick.invoke() }, title = "New Medication",
            modifier = Modifier.padding(bottom = 16.dp)
        )
        FlySimpleCard(
            isPrimaryBackground = true
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Name input.
                item {
                    GTextField(
                        value = state.value.medName,
                        onValueChange = { viewModel.updateMedName(it) },
                        label = "Name",
                        isPrimaryColor = false,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                // Form.
                item {
                    var selectedForm by remember { mutableStateOf(state.value.medForm) }
                    val optionsArray: Array<MedicationForm> =
                        MedicationForm.entries.toTypedArray()
                    val opList: List<String> =
                        optionsArray.map { it.toString() }
                    // val options = MedicationForm.entries
                    // items = listOf("Item 1", "Item 2", "Item 3")
                    DropdownMenuExample(items = opList) { selected ->
                        viewModel.updateMedForm(selected)
                        // selectedForm = selected
                    }
                    /*             DropdownMenuExample(items = opList) { selected ->
                                    selectedForm = selected
                                } */
                    
                    Text(
                        stringResource(R.string.add_new_med_form_screen_title),
                        style = MedTrackerTheme.typography.title2,
                    )
                    /*               Row(
                                      modifier = Modifier.fillMaxWidth(),
                                      horizontalArrangement = Arrangement.SpaceBetween
                                  ) {
                                      options.forEach { form ->
                                          GRadioButton(
                                              selected = selectedForm == form,
                                              onClick = { selectedForm = form },
                                              caption = form.toString()
                                                  .lowercase()
                                          )
                                      }
                                  } */
                }
                /*             item {
                                var selectedForm =
                                    remember { mutableStateOf(state.value.medForm).toString() }
                                val options = MedicationForms.entries.toTypedArray()
                                
                                LargeDropdownMenu(
                                    items = state.value.medicationForms,
                                    label = "Medication Form"
                                ) { selectedItem ->
                                    selectedForm = selectedItem
                                    println("Selected: $selectedItem")
                                }
                                
                            } */
                // Strength.
                item {
                    var selectedUnit by remember { mutableStateOf(state.value.medUnit) }
                    val unitOptions = MedicationUnit.entries.toTypedArray()
                    
                    FlySimpleCard(
                        isPrimaryBackground = false,
                        content = {
                            // Spacer(modifier = Modifier.padding(8.dp))
                            GTextField(
                                value = state.value.medStrength.toString(),
                                onValueChange = { viewModel.updateMedStrength(it.toFloat()) },
                                label = "Medication Strength",
                                isPrimaryColor = true,
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
                                        Text(
                                            text = unit.toString()
                                                .lowercase()
                                        )
                                        GRadioButton(
                                            selected = selectedUnit == unit,
                                            onClick = {
                                                viewModel.updateMedUnit(
                                                    selectedUnit
                                                )
                                                selectedUnit = unit
                                            })
                                    }
                                }
                            }
                        })
                }
                // Start and end dates + time.
                item {
                    // Выбор начала и конца периода приема.
                    Text(
                        text = "Set Medication Period",
                        style = MedTrackerTheme.typography.title2Emphasized,
                    )
                    ModalDatePicker(viewModel)
                    var showTimePicker by remember { mutableStateOf(false) }
                    
                    GSecondaryButton(
                        shape = MedTrackerTheme.shapes.extraLarge,
                        onClick = { showTimePicker = true }) {
                        Text("Set time")
                    }
                    // Время приема.
                    if (showTimePicker) {
                        IntakeTimePicker(
                            onConfirm = {
                                showTimePicker = false
                            }, onDismiss = { showTimePicker = false }, viewModel
                        )
                    }
                }
                // Дни недели.
                item {
                    FlySimpleCard(
                        isPrimaryBackground = false,
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
                        })
                }
                // button to add medication.
                item {
                    val context = LocalContext.current
                    GPrimaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            viewModel.addMedication(context)
                            onConfirmClick.invoke()
                        },
                        content = {
                            Text(text = "Add Medication")
                        }
                    )
                }
            }
        }
    }
    
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalDatePicker(
    viewModel: AddNewMedViewModel
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    var showPicker by remember { mutableStateOf(false) }
    val label = when {
        state.value.medStartDate != null && state.value.medEndDate != null -> "Start: ${
            formatTimestampTillTheDay(
                state.value.medStartDate!!
            )
        }\nEnd: ${formatTimestampTillTheDay(state.value.medEndDate!!)}"
        
        state.value.medStartDate != null -> "Start: ${
            formatTimestampTillTheDay(
                state.value.medStartDate!!
            )
        }"
        
        state.value.medEndDate != null -> "End: ${
            formatTimestampTillTheDay(
                state.value.medEndDate!!
            )
        }"
        
        else -> ""
    }
    Column(
    ) {
        GTextField(
            modifier = Modifier.fillMaxWidth(),
            minLines = 2,
            value = "",
            label = label,
            onValueChange = {},
            singleLine = false,
            readOnly = true,
        )
        GOutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { showPicker = !showPicker }) {
            Text(text = "Select start and end dates")
        }
        
        
        if (showPicker) {
            DateRangePickerModal(
                onDateRangeSelected = {
                    viewModel.updateStartDate(
                        parseDateForFirestore(
                            convertMillisToDate(it.first)
                        )
                    )
                    viewModel.updateEndDate(
                        parseDateForFirestore(
                            convertMillisToDate(it.second)
                        )
                    )
                    showPicker = !showPicker
                },
                onDismiss = { showPicker = !showPicker },
            )
        }
    }
    
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerModal(
    onDateRangeSelected: (Pair<Long?, Long?>) -> Unit, onDismiss: () -> Unit
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
                }) {
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
    onConfirm: () -> Unit, onDismiss: () -> Unit, viewModel: AddNewMedViewModel
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        androidx.compose.material3.Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .width(IntrinsicSize.Max),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Select Intake Time",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                val currentTime = Calendar.getInstance()
                val timePickerState = rememberTimePickerState(
                    initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
                    initialMinute = currentTime.get(Calendar.MINUTE),
                    is24Hour = false,
                )
                val time =
                    LocalTime.of(timePickerState.hour, timePickerState.minute)
                val dtf = DateTimeFormatter.ofPattern("HH:mm")
                
                TimePicker(
                    state = timePickerState,
                    colors = TimePickerDefaults.colors(
                        clockDialColor = MaterialTheme.colorScheme.surfaceVariant,
                        clockDialSelectedContentColor = MaterialTheme.colorScheme.onSurface,
                        clockDialUnselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        selectorColor = MaterialTheme.colorScheme.primary,
                        periodSelectorBorderColor = MaterialTheme.colorScheme.outline,
                        periodSelectorSelectedContentColor = MaterialTheme.colorScheme.onSurface,
                        periodSelectorUnselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        periodSelectorSelectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        timeSelectorSelectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        timeSelectorUnselectedContainerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                )
                
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextButton(
                        onClick = onDismiss,
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colorScheme.error
                        )
                    ) {
                        Text("Cancel")
                    }
                    
                    GPrimaryButton(
                        onClick = {
                            viewModel.updateIntakeTime(time.format(dtf))
                            onConfirm()
                        },
                    ) {
                        Text("Confirm", color = MedTrackerTheme.colors.sysWhite)
                    }
                }
            }
        }
    }
    /*     val currentTime = Calendar.getInstance()
        val timePickerState = rememberTimePickerState(
            initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
            initialMinute = currentTime.get(Calendar.MINUTE),
            is24Hour = false,
        )
        val time = LocalTime.of(timePickerState.hour, timePickerState.minute)
        val dtf = DateTimeFormatter.ofPattern("HH:mm")
        
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TimePicker(
                state = timePickerState, colors = TimePickerDefaults.colors(
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
        } */
    
}

fun convertMillisToDate(timeInMillis: Long?): String {
    val formatter =
        DateTimeFormatter.ofPattern("MMMM dd yyyy", Locale.getDefault())
            .withZone(ZoneId.systemDefault())
    
    return timeInMillis?.let { formatter.format(Instant.ofEpochMilli(it)) }
        ?: "N/A"
}/*
fun convertMillisToDate(timeInMillis: Long?): String {
  val formatter = SimpleDateFormat("MMMM dd yyyy", Locale.US)
  
  if (timeInMillis != null) {
    return formatter.format(Date(timeInMillis))
  }
  
  return ""
}*/
