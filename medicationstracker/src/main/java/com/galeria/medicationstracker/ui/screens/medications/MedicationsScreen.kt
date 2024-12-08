package com.galeria.medicationstracker.ui.screens.medications

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.galeria.medicationstracker.ui.components.FlyTextButton
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

@Composable
fun MedicationsScreen(
  modifier: Modifier = Modifier,
  onAddMedClick: () -> Unit = {},
  onOpenMedClick: () -> Unit = {},
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
    Spacer(modifier = Modifier.padding(vertical = 8.dp))

    LazyColumn(
      verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {

      items(medications) { med ->
        SimpleCardComponent(
          title = med.name.toString(),
          topEndActionLabel = "Edit",
          description = med.form.toString(),
          onTopEndActionClick = {
            onOpenMedClick.invoke()
          },
          onRemoveMedClick = {
            medicationsViewModel.deleteMedicationFromFirestore(med.name.toString())
          }
        )
      }
    }
    // Button to add a new medication.
    FlyButton(
      onClick = { onAddMedClick.invoke() },
      Modifier.fillMaxWidth()
    ) {
      Text("+ Add Medication")
    }

  }
}

@Composable
fun SimpleCardComponent(
  modifier: Modifier = Modifier,
  title: String = "Header",
  topEndActionLabel: String? = "Top End Text",
  description: String = "Description",
  onTopEndActionClick: () -> Unit,
  onRemoveMedClick: () -> Unit,
) {

  ElevatedCard(modifier = modifier) {
    Row(
      Modifier
        .fillMaxWidth()
        .padding(
          horizontal = dimensionResource(R.dimen.card_content_padding_horizontal),
          vertical = dimensionResource(R.dimen.card_content_padding_vertical),
        ),
      verticalAlignment = Alignment.Top,

      ) {
      Column(
        Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),

        ) {
        Text(title, style = MedTrackerTheme.typography.body)
        Text(description, style = MedTrackerTheme.typography.subhead)
      }

      Spacer(modifier.weight(1f))

      Column(
        Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        horizontalAlignment = Alignment.CenterHorizontally

      ) {
        NavigationRow({ onTopEndActionClick() }, topEndActionLabel ?: "")

        FlyTextButton(
          onClick = onRemoveMedClick,
        ) {
          Text("Delete")
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
