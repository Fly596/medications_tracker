package com.galeria.medicationstracker.ui.screens.medications

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
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
import com.galeria.medicationstracker.ui.shared.components.HIGButtonStyle
import com.galeria.medicationstracker.ui.theme.MedicationsTrackerAppTheme
import java.util.Date
import java.util.Locale

@Composable
fun MedicationsScreen(
  modifier: Modifier = Modifier,
  viewModel: MedicationsViewModel = viewModel(),
) {
  val uid = viewModel.userId

  val state = viewModel.uiState

  Column {
    // Header.
    Text(
      stringResource(R.string.meds_screen_title),
      style = MedicationsTrackerAppTheme.extendedTypography.largeTitle,
    )

    Spacer(modifier = Modifier.padding(8.dp))

    HIGButton(
      text = "Add Medication",
      onClick = { /* TODO: Open Form */ },
      enabled = true,
      style = HIGButtonStyle.Bezeled,
      Modifier.fillMaxWidth(),
    )

    MyTextField(
      value = state.name,
      onValueChange = { viewModel.updateMedName(it) },
      label = "Medication name",
      modifier = Modifier.fillMaxWidth(),
    )
    MyTextField(
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
    )

    val context = LocalContext.current

    HIGButton(
      text = "Submit",
      onClick = {
        val newMed = TEMP_Medication(uid = uid, name = state.name, type = state.type)

        viewModel.addMedication(newMed, context)
      },
      enabled = true,
    )

    /*     if (viewModel.showDatePicker){
      DateRangePickerModal({},{})
    } */
    // Start end selection.

    // Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
    HIGButton(
      text = "Start Date",
      onClick = { viewModel.showDatePicker = !viewModel.showDatePicker },
      enabled = true,
    )
    // ModalDatePicker(viewModel, updateDate = { viewModel.updateEndDate(it) })

    ModalDatePicker(viewModel)

    // }

    LazyColumn(
      verticalArrangement = Arrangement.spacedBy(8.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = modifier.fillMaxSize(),
    ) {

      // TODO: get medications from firebase.

      viewModel.userMedications.value.forEach { medication ->
        item {
          CardComponent(
            header = medication.type,
            topEndText = "Edit",
            content = medication.name,
            onClick = {
              // TODO: Реализовать открытие экрана с выбранным medication.
            },
          )
        }
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalDatePicker(viewModel: MedicationsViewModel) {
  var selectedDate = viewModel.selectedStartDate
  var selectEndDate = viewModel.selectedEndDate

  if (
  /* viewModel.selectedStartDate != null &&
  viewModel.selectedEndDate != null && */
    viewModel.showDatePicker
  ) {
    DateRangePickerModal(
      onDateRangeSelected = {
        selectedDate = it.first
        selectEndDate = it.second
      },
      onDismiss = { viewModel.showDatePicker = !viewModel.showDatePicker },
    )

    /*  val dateS = Date(selectedDate!!)
    val dateE = Date(selectEndDate!!)
    val formatedDateS = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(dateS)
    val formatedDateE = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(dateE)
    Text("Selected date: $formatedDateS to $formatedDateE") */
  } else {
    Text("No data selected.")

    // val dateS = Date(selectedDate!!)
    // val dateE = Date(selectEndDate!!)
    // val formatedDateS = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(dateS)
    // val formatedDateE = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(dateE)

    // Text("Selected date: $formatedDateS to $formatedDateE")
  }
}

@Composable
fun NavigationRow(onClick: () -> Unit, label: String) {
  Row(
    modifier = Modifier.clickable(onClick = onClick),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(4.dp),
  ) {
    Text(label, style = MaterialTheme.typography.labelSmall)
    Icon(
      imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
      contentDescription = null,
      tint = MaterialTheme.colorScheme.secondary,
      modifier = Modifier.size(10.dp),
    )
  }
}

@Composable
fun CardComponent(
  header: String,
  topEndText: String?,
  content: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  ElevatedCard(modifier = modifier) {
    Column(
      modifier =
      Modifier.padding(
        horizontal = dimensionResource(R.dimen.card_padding_horizontal),
        vertical = dimensionResource(R.dimen.card_padding_vertical),
      ),
      verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
    ) {
      Row(modifier = Modifier.fillMaxWidth()) {
        Text(header)
        Spacer(modifier.weight(1f))
        NavigationRow({ onClick() }, topEndText ?: "")
      }
      Text(content, style = MaterialTheme.typography.headlineMedium)
    }
  }
}

/*   Column(modifier = modifier.fillMaxWidth().padding(12.dp), horizontalAlignment = Alignment.Start) {
    OutlinedTextField(
      value = datePick,
      onValueChange = { updateDate(it) },
      label = { Text("Set End Date") },
      readOnly = true,
      trailingIcon = {
        IconButton(onClick = { viewModel.showDatePicker = true }) {
          Icon(imageVector = Icons.Default.DateRange, contentDescription = "Select date")
        }
      },
      modifier = Modifier.fillMaxWidth().height(64.dp),
    )
    if (viewModel.showDatePicker) {
      DateRangePickerModal(
        onDateRangeSelected = {
        }

      ) { }

       DatePickerModal(
        datePickerState = datePickerState,
        onDateSelected = { it?.let { updateDate(convertMillisToDate(it)) } },
      ) {
        viewModel.showDatePicker = false
      }
    }
  }
}*/

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
          onDismiss()
        }
      ) {
        Text("OK")
      }
    },
    dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } },
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

fun convertMillisToDate(millis: Long): String {
  val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
  return formatter.format(Date(millis))
}

@Preview
@Composable
fun CardComponentPreview() {
  Column {
    DateRangePickerModal({}, {})
    // CardComponent("Header", "Top End Text", "Content",{})
    // ModalDatePicker()
  }
}
