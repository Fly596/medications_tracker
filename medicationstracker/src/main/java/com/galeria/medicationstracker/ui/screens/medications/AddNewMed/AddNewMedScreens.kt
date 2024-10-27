package com.galeria.medicationstracker.ui.screens.medications.AddNewMed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.data.MedicationForm
import com.galeria.medicationstracker.data.MedicationUnit
import com.galeria.medicationstracker.ui.screens.autentification.login.MyTextField
import com.galeria.medicationstracker.ui.shared.components.HIGButton
import com.galeria.medicationstracker.ui.theme.MedicationsTrackerAppTheme

@Composable
fun AddNewMedNameScreen(onNextClick: () -> Unit, viewModel: AddNewMedViewModel = viewModel()) {
  val uid = viewModel.userId

  val state = viewModel.uiState

  Column(modifier = Modifier
    .fillMaxSize()
    .padding(top = 32.dp)) {
    // Header.
    Text(
      stringResource(R.string.add_new_med_name_screen_title),
      style = MedicationsTrackerAppTheme.extendedTypography.largeTitle,
    )

    Spacer(modifier = Modifier.padding(8.dp))

    MyTextField(
      value = state.name,
      onValueChange = { viewModel.updateMedName(it) },
      label = "Medication name",
      modifier = Modifier.fillMaxWidth(),
    )

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

  var selectedOption by remember { mutableStateOf(MedicationForm.TABLET) }
  val options = MedicationForm.entries.toTypedArray()

  Column(modifier = Modifier
    .fillMaxSize()
    .padding(top = 32.dp)) {
    // Header.
    Text(
      stringResource(R.string.add_new_med_form_screen_title),
      style = MedicationsTrackerAppTheme.extendedTypography.largeTitle,
    )

    Spacer(modifier = Modifier.padding(8.dp))

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
      options.forEach { form ->
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(text = form.toString())
          RadioButton(selected = selectedOption == form, onClick = { selectedOption = form })
        }
      }
    }

    Spacer(modifier = Modifier.padding(8.dp))

    HIGButton(
      text = "Next",
      onClick = {
        viewModel.updateMedForm(selectedOption)
        onNextClick.invoke()
      },
      modifier = Modifier.fillMaxWidth(),
    )
  }
}

@Composable
fun NewMedStrengthScreen(viewModel: AddNewMedViewModel = viewModel(), onNextClick: () -> Unit) {
  val state = viewModel.uiState
  var selectedOption by remember { mutableStateOf(MedicationUnit.MG) }
  val options = MedicationUnit.entries.toTypedArray()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(top = 32.dp)
  ) {
    // Header.
    Text(
      stringResource(R.string.add_new_med_strength_screen_title),
      style = MedicationsTrackerAppTheme.extendedTypography.largeTitle,
    )

    Spacer(modifier = Modifier.padding(8.dp))

    MyTextField(
      value = state.strength.toString(),
      onValueChange = { viewModel.updateMedName(it) },
      label = "Medication Strength",
      modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.padding(8.dp))

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
      options.forEach { unit ->
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(text = unit.toString())
          RadioButton(selected = selectedOption == unit, onClick = { selectedOption = unit })
        }
      }
    }

    Spacer(modifier = Modifier.padding(8.dp))

    HIGButton(
      text = "Next",
      onClick = {
        viewModel.updateMedUnit(selectedOption)
        onNextClick.invoke()
      },
      modifier = Modifier.fillMaxWidth(),
    )
  }
}

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
