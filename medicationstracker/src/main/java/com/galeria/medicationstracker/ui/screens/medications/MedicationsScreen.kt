package com.galeria.medicationstracker.ui.screens.medications

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import androidx.lifecycle.compose.*
import androidx.lifecycle.viewmodel.compose.*
import com.galeria.medicationstracker.ui.components.*

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
      .fillMaxSize(),
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
      
      item {
        // Button to add a new medication.
        FlyButton(
          onClick = { onAddMedClick.invoke() },
          Modifier.fillMaxWidth()
        ) {
          Text("+ Add Medication")
        }
      }
    }
    
  }
}
