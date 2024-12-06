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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.ui.components.FlyButton
import com.galeria.medicationstracker.ui.components.FlyElevatedCard
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

@Composable
fun MedicationsScreen(
  modifier: Modifier = Modifier,
  onNewMedClick: () -> Unit,
  viewModel: MedicationsViewModel = viewModel(),
) {

  Column(
    modifier = modifier.fillMaxSize(),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // Header.
    Text(
      stringResource(R.string.meds_screen_title),
      style = MedTrackerTheme.typography.largeTitleEmphasized,
    )

    // Spacer(modifier = Modifier.padding(16.dp))

    FlyButton(onClick = { onNewMedClick.invoke() }, Modifier.fillMaxWidth()) {
      Text("+ Add Medication")
    }

    // Spacer(modifier = Modifier.padding(24.dp))

    LazyColumn(
      verticalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier
    ) {

      // TODO: get medications from firebase.

      viewModel.userMedications.value.forEach { medication ->
        item {
          CardComponent(
            header = medication.name,
            topEndText = "Edit",
            content = medication.form.toString(),
            onClick = {
              // TODO: Реализовать открытие экрана с выбранным medication.
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
  header: String = "Header",
  topEndText: String? = "Top End Text",
  content: String = "Content",
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  FlyElevatedCard(modifier = modifier) {
    Column(
      modifier =
        Modifier.padding(
          horizontal = dimensionResource(R.dimen.card_padding_horizontal),
          vertical = dimensionResource(R.dimen.card_padding_vertical),
        ),
      verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
    ) {
      Row(modifier = Modifier.fillMaxWidth()) {
        Text(header, style = MedTrackerTheme.typography.body)
        Spacer(modifier.weight(1f))
        NavigationRow({ onClick() }, topEndText ?: "")
      }
      Text(content, style = MedTrackerTheme.typography.subhead)
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
