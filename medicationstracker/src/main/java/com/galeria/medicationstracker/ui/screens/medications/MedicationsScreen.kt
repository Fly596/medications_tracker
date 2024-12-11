package com.galeria.medicationstracker.ui.screens.medications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.ui.components.FlyButton
import com.galeria.medicationstracker.ui.components.FlyElevatedCardMedsList
import com.galeria.medicationstracker.ui.components.FlyTextButton
import com.galeria.medicationstracker.ui.components.FlyTopAppBar
import com.galeria.medicationstracker.ui.components.NavigationRow
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicationsScreen(
  modifier: Modifier = Modifier,
  onAddMedClick: () -> Unit = {},
  onEditMedClick: () -> Unit = {},
  medicationsViewModel: MedicationsViewModel = viewModel(),
) {
  val medications by
  medicationsViewModel.userMedications.collectAsStateWithLifecycle()

  Scaffold(
    topBar = {
      FlyTopAppBar("My Meds")
    },
    containerColor = MedTrackerTheme.colors.secondaryBackground,
    content = {
      Column(
        modifier = modifier
          .fillMaxSize()
          .padding(it)
          .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        LazyColumn(
          verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

          items(medications) { med ->
            FlyElevatedCardMedsList(
              title = med.name.toString(),
              dosage = ("${med.strength.toString()} ${
                (med.unit).toString().lowercase()
              }"),
              info = med.form.toString().lowercase(),
              onEditClick = {onEditMedClick.invoke()},
              onRemoveMedClick = {
                medicationsViewModel.deleteMedicationFromFirestore(med.name.toString())
              }
            )

      /*       SimpleCardComponent(
              title = med.name.toString(),
              topEndActionLabel = "Edit",
              description = med.form.toString(),
              onTopEndActionClick = {
                onEditMedClick.invoke()
              },
              onRemoveMedClick = {
                medicationsViewModel.deleteMedicationFromFirestore(med.name.toString())
              }
            ) */
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
  )


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

