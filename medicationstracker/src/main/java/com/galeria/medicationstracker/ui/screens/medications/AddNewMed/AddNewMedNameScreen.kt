package com.galeria.medicationstracker.ui.screens.medications.AddNewMed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.ui.screens.autentification.login.MyTextField
import com.galeria.medicationstracker.ui.shared.components.HIGButton
import com.galeria.medicationstracker.ui.theme.MedicationsTrackerAppTheme

@Composable
fun AddNewMedNameScreen(
  onNextClick: () -> Unit,
  viewModel: AddNewMedViewModel = viewModel()
) {
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
      modifier = Modifier.fillMaxWidth()
    )
  }
}

@Composable
fun AddNewMedFormScreen(viewModel: AddNewMedViewModel = viewModel()) {
  var state = viewModel.uiState

  Column(modifier = Modifier
    .fillMaxSize()
    .padding(top = 32.dp)) {
    // Header.
    Text(
      stringResource(R.string.add_new_med_form_screen_title),
      style = MedicationsTrackerAppTheme.extendedTypography.largeTitle,
    )

    Spacer(modifier = Modifier.padding(8.dp))

    var checkState by remember { mutableStateOf(true) }
    Row(modifier = Modifier.selectableGroup()) {
      /*RadioButton(
        selected = checkState,
        onClick = {
          checkState = true
          state.form = MedicationForm.TABLET
                  },
      )
       RadioButton(
        selected = !checkState,
        onClick = { checkState = false },
      )
      RadioButton(
        selected = !checkState,
        onClick = { checkState = false },
      )
      RadioButton(
        selected = !checkState,
        onClick = { checkState = false },
      ) */
    }

    Spacer(modifier = Modifier.padding(8.dp))

    val context = LocalContext.current
    HIGButton(
      text = "Next",
      onClick = {
      },
      modifier = Modifier.fillMaxWidth()
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
