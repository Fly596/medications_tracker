package com.galeria.medicationstracker.ui.screens.medications

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
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.ui.components.FlyButton
import com.galeria.medicationstracker.ui.components.FlyElevatedCard
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

@Composable
fun MedicationsScreen(
  modifier: Modifier = Modifier,
  onAddMedicationClick: () -> Unit = {},
  medicationsViewModel: MedicationsViewModel = viewModel(),
) {
  val medications by
  medicationsViewModel.userMedications.collectAsStateWithLifecycle()

  Column(
    modifier = modifier.fillMaxSize(),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // Displays the screen title.
    Text(
      stringResource(R.string.meds_screen_title),
      style = MedTrackerTheme.typography.largeTitleEmphasized,
    )

    // Button to add a new medication.
    FlyButton(
      onClick = { onAddMedicationClick.invoke() },
      Modifier.fillMaxWidth()
    ) {
      Text("+ Add Medication")
    }

    LazyColumn(
      verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {

      items(medications) { med ->
        CardComponent(
          title = med.name.toString(),
          topEndText = "Edit",
          description = med.form.toString(),
          onClick = {
            // TODO: Navigates to medication details screen.
          }
        )
      }
    }
  }
}

@Composable
fun CardComponent(
  title: String = "Header",
  topEndText: String? = "Top End Text",
  description: String = "Description",
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  ElevatedCard(modifier = modifier) {
    Column(
      modifier =
        Modifier.padding(
          horizontal = dimensionResource(R.dimen.card_content_padding_horizontal),
          vertical = dimensionResource(R.dimen.card_content_padding_vertical),
        ),
      verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
    ) {
      Row(modifier = Modifier.fillMaxWidth()) {
        Text(title, style = MedTrackerTheme.typography.body)
        Spacer(modifier.weight(1f))
        NavigationRow({ onClick() }, topEndText ?: "")
      }
      Text(description, style = MedTrackerTheme.typography.subhead)
    }
  }

}

@Composable
fun NavigationRow(onClick: () -> Unit, label: String) {
  Row(
    modifier = Modifier.clickable(onClick = onClick),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(4.dp),
  ) {
    Text(label, style = MedTrackerTheme.typography.body)
    Icon(
      imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
      contentDescription = null,
      tint = MedTrackerTheme.colors.secondary600,
      modifier = Modifier.size(10.dp),
    )
  }
}

/* @Composable
fun MedicationsScreen(
  modifier: Modifier = Modifier,
  onAddMedicationClick: () -> Unit,
  medicationsViewModel: MedicationsViewModel = viewModel(),
) {
  Column(
    modifier = modifier.fillMaxSize(),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // Displays the screen title.
    Text(
      stringResource(R.string.meds_screen_title),
      style = MedTrackerTheme.typography.largeTitleEmphasized,
    )

    // Button to add a new medication.
    FlyButton(
      onClick = { onAddMedicationClick.invoke() },
      Modifier.fillMaxWidth()
    ) {
      Text("+ Add Medication")
    }

    // Displays the list of medications.
    LazyColumn(
      verticalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier
    ) {

      // Iterates through the user's medications.
      medicationsViewModel.userMmedication.value.forEach { medication ->
        item {
          // Card to display medication info.
          CardComponent(
            title = medication.name,
            topEndText = "Edit",
            description = medication.notes,
            onClick = {
              // TODO: Navigates to medication details screen.
            },
          )
        }
      }
    }
  }
}

@Composable
fun NavigationRow(onClick: () -> Unit, label: String) {

  Row(
    modifier = Modifier.clickable(onClick = onClick),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(4.dp),
  ) {
    Text(label, style = MedTrackerTheme.typography.body)
    Icon(
      imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
      contentDescription = null,
      tint = MedTrackerTheme.colors.secondary600,
      modifier = Modifier.size(10.dp),
    )
  }
}

@Composable
fun CardComponent(
  title: String = "Header",
  topEndText: String? = "Top End Text",
  description: String = "Description",
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  FlyElevatedCard(modifier = modifier) {
    Column(
      modifier =
        Modifier.padding(
          horizontal = dimensionResource(R.dimen.card_content_padding_horizontal),
          vertical = dimensionResource(R.dimen.card_content_padding_vertical),
        ),
      verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
    ) {
      Row(modifier = Modifier.fillMaxWidth()) {
        Text(title, style = MedTrackerTheme.typography.body)
        Spacer(modifier.weight(1f))
        NavigationRow({ onClick() }, topEndText ?: "")
      }
      Text(description, style = MedTrackerTheme.typography.subhead)
    }
  }
} */

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

@Preview
@Composable
fun CardComponentPreview() {
  MedTrackerTheme {
    Column {
      CardComponent(
        onClick = {}
      )
      // CardComponent("Header", "Top End Text", "Content",{})
      // ModalDatePicker()
    }
  }

}
