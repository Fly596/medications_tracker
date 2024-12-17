package com.galeria.medicationstracker.ui.screens.medications.update

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.text.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.*
import androidx.lifecycle.compose.*
import androidx.lifecycle.viewmodel.compose.*
import com.galeria.medicationstracker.data.*
import com.galeria.medicationstracker.ui.components.*
import com.galeria.medicationstracker.ui.screens.medications.*
import com.galeria.medicationstracker.ui.theme.*
import java.time.*
import java.time.format.*
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateMedScreen(
  passedMedName: String,
  modifier: Modifier = Modifier,
  viewModel: UpdateMedVM = viewModel(),
  onConfirmEdit: () -> Unit = {},
) {
  
  LaunchedEffect(Unit) { viewModel.getMedicationFromFirestore(passedMedName) }
  val state = viewModel.uiState
  
  val currentMed by viewModel.selectedMedication.collectAsStateWithLifecycle()
  
  Scaffold(
    topBar = {
      FlyTopAppBar("Edit medication")
    },
    containerColor = MedTrackerTheme.colors.primaryBackground,
    content = {
      Column(
        modifier = modifier
          .fillMaxSize()
          .padding(it)
          .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        
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
              placeholder = currentMed?.name,
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
              modifier = Modifier.fillMaxWidth(),
            )
          }
          
          // form.
          item {
            var selectedForm by remember { mutableStateOf(state.medForm) }
            val options = MedicationForms.entries.toTypedArray()
            
            Text(
              text = "Form",
              style = MedTrackerTheme.typography.title2,
            )
            Spacer(modifier = Modifier.padding(4.dp))
            
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
          
          // End Date input
          item {
            DatePicker(viewModel)
          }
// Start Date input
          
          item {
            DayOfWeekSelector(
              viewModelUpd = viewModel
            )
            
          }
          
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
              placeholder = currentMed?.notes,
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
              placeholder = currentMed?.strength.toString(),
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
              modifier = Modifier.fillMaxWidth(),
            )
            // Add a unit selector or dropdown for strength units (e.g., MG, ML)
            // ...
          }
          
          item {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              FlyButton(
                // TODO: Add editing medication logic.
                onClick = {
                  viewModel.updateMedicationFromFirestore()
                }
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
        
      }
    }
  )
  
  
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