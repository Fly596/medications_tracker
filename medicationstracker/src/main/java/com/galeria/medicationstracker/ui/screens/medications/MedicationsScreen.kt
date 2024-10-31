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
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.galeria.medicationstracker.ui.shared.components.HIGButton
import com.galeria.medicationstracker.ui.shared.components.HIGButtonStyle
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

@Composable
fun MedicationsScreen(
  modifier: Modifier = Modifier,
  onNewMedClick: () -> Unit,
  viewModel: MedicationsViewModel = viewModel(),
) {

  Column {
    // Header.
    Text(
      stringResource(R.string.meds_screen_title),
      style = MedTrackerTheme.typography.largeTitle,
    )

    Spacer(modifier = Modifier.padding(8.dp))

    HIGButton(
      text = "+ Add Medication",
      onClick = { onNewMedClick.invoke() },
      enabled = true,
      style = HIGButtonStyle.Bezeled,
      Modifier.fillMaxWidth(),
    )

    Spacer(modifier = Modifier.padding(24.dp))

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


@Composable
fun NavigationRow(onClick: () -> Unit, label: String) {
  Row(
    modifier = Modifier.clickable(onClick = onClick),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(4.dp),
  ) {
    Text(label, style = MedTrackerTheme.typography.caption1)
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
        Text(
          header,
          style = MedTrackerTheme.typography.subhead
        )
        Spacer(modifier.weight(1f))
        NavigationRow({ onClick() }, topEndText ?: "")
      }
      Text(content, style = MedTrackerTheme.typography.title3)
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
  Column {
    DateRangePickerModal({}, {})
    // CardComponent("Header", "Top End Text", "Content",{})
    // ModalDatePicker()
  }
}
