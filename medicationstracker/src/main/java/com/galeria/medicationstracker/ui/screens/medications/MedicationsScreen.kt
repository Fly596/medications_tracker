package com.galeria.medicationstracker.ui.screens.medications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.ui.components.FlyButton
import com.galeria.medicationstracker.ui.components.FlyElevatedCardMedsList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicationsScreen(
  modifier: Modifier = Modifier,
  onAddMedClick: () -> Unit = {},
  onViewMedClick: (String) -> Unit = {},
  medicationsViewModel: MedicationsViewModel = viewModel(),
) {
  val medications by
  medicationsViewModel.userMedications.collectAsStateWithLifecycle()

  Column(
    modifier = modifier
      .fillMaxSize()
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
          dosage = ("${med.strength} ${med.unit.toString().lowercase()}"),
          info = med.form.toString().lowercase(),
          onEditClick = { onViewMedClick(med.name.toString()) },
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
