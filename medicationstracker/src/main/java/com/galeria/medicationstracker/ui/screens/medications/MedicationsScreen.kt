package com.galeria.medicationstracker.ui.screens.medications

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.data.TEMP_Medication
import com.galeria.medicationstracker.ui.screens.autentification.login.MyTextField
import com.galeria.medicationstracker.ui.shared.components.HIGButton
import com.google.firebase.appcheck.internal.util.Logger.TAG
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun MedicationsScreen(
    modifier: Modifier = Modifier,
    viewModel: MedicationsViewModel = viewModel(),
) {
  val uid = viewModel.userId

  val meds = viewModel.userMedications.collectAsState().value
  Log.d(TAG, "${meds.size}")

  Column {
    Text(
        stringResource(R.string.meds_screen_title),
        style = MaterialTheme.typography.headlineMedium,
    )

    Spacer(modifier = Modifier.padding(8.dp))

    MyTextField(
        value = viewModel.uiState.name,
        onValueChange = { viewModel.updateMedName(it) },
        label = "Medication name",
        modifier = Modifier.fillMaxWidth(),
    )
    MyTextField(
        value = viewModel.uiState.type,
        onValueChange = { viewModel.updateMedType(it) },
        label = "Medication type",
        modifier = Modifier.fillMaxWidth(),
    )
    MyTextField(
        value = viewModel.uiState.strength.toString(),
        onValueChange = { viewModel.updateMedStrength(it.toFloat()) },
        label = "Strength",
        modifier = Modifier.fillMaxWidth(),
    )
    MyTextField(
        value = viewModel.uiState.notes,
        onValueChange = { viewModel.updateMedNotes(it) },
        label = "Notes",
        modifier = Modifier.fillMaxWidth(),
    )

    val context = LocalContext.current

    HIGButton(
        text = "Submit",
        onClick = {
          val newMed =
              TEMP_Medication(
                  uid = uid,
                  name = viewModel.uiState.name,
                  type = viewModel.uiState.type,
              )

          viewModel.addMedication(newMed, context)
        },
        enabled = true,
    )

    HIGButton(
        text = "Get Data",
        onClick = {

          // val newMed = TEMP_Medication(uid = uid, name = medName.value, type = medType.value)
          runBlocking { launch { viewModel.getMedsList() } }
        },
        enabled = true,
    )

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize(),
    ) {

      // TODO: get medications from firebase.
      items(meds) { medication ->
        CardComponent(
            header = medication.type,
            topEndText = "Edit",
            content = medication.name,
            onClick = {
              // TODO: Реализовать открытие экрана с выбранным medication.
            })
      }
    }
  }
}

@Composable
fun CardComponent(
    header: String,
    topEndText: String?,
    content: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
  ElevatedCard(modifier = modifier) {
    Column(
        modifier =
            Modifier.padding(
                horizontal = dimensionResource(R.dimen.card_padding_horizontal),
                vertical = dimensionResource(R.dimen.card_padding_vertical)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))) {
          Row(modifier = Modifier.fillMaxWidth()) {
            Text(header)
            Spacer(modifier.weight(1f))
            NavigationRow({ onClick() }, topEndText ?: "")
          }
          Text(content, style = MaterialTheme.typography.headlineMedium)
        }
  }
}

@Composable
fun NavigationRow(onClick: () -> Unit, label: String) {
  Row(
      modifier = Modifier.clickable(onClick = onClick),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(label, style = MaterialTheme.typography.labelSmall)
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.size(10.dp))
      }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModalInput(onDateSelected: (Long?) -> Unit, onDismiss: () -> Unit) {
  val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)

  DatePickerDialog(
      onDismissRequest = onDismiss,
      confirmButton = {
        TextButton(
            onClick = {
              onDateSelected(datePickerState.selectedDateMillis)
              onDismiss()
            }) {
              Text("OK")
            }
      },
      dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } }) {
        DatePicker(state = datePickerState)
      }
}

@Preview
@Composable
fun CardComponentPreview() {
  DatePickerModalInput({}, {})
}
