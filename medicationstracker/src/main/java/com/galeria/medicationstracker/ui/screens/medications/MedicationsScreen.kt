package com.galeria.medicationstracker.ui.screens.medications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.data.UserMedication
import com.galeria.medicationstracker.ui.components.FlyButton
import com.galeria.medicationstracker.ui.components.FlyTextButton
import com.galeria.medicationstracker.ui.components.NavigationRow
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicationsScreen(
    modifier: Modifier = Modifier,
    onAddMedClick: () -> Unit = {},
    onViewMedClick: (String) -> Unit = {},
    medicationsViewModel: MedicationsViewModel = viewModel(),
) {

  LaunchedEffect(Unit) { medicationsViewModel.fetchUserMedications() }

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

@Composable
fun FlyElevatedCardMedsList(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    title: String = "Medicine Name",
    dosage: String = "50 mg",
    info: String = "Mon, Tue, Fri...",
    onEditClick: () -> Unit,
    onRemoveMedClick: () -> Unit,
    medication: UserMedication? = null,
    shape: Shape = RoundedCornerShape(8.dp),
    elevation: CardElevation = CardDefaults.elevatedCardElevation(),
) {
  ElevatedCard(
    modifier = modifier
        .fillMaxWidth()
        .height(120.dp),
    shape = RoundedCornerShape(16.dp),
    elevation = CardDefaults.elevatedCardElevation(
      defaultElevation = 1.dp,
      pressedElevation = 8.dp,
      focusedElevation = 10.dp,
    ),
    colors =
      CardDefaults.elevatedCardColors(
        containerColor = MedTrackerTheme.colors.primaryBackground,
        contentColor = MedTrackerTheme.colors.primaryLabel,
        disabledContainerColor = MedTrackerTheme.colors.primaryTinted,
        disabledContentColor = MedTrackerTheme.colors.secondary600
      )
  ) {
    Row(
      modifier = Modifier
          .fillMaxSize()
          .padding(16.dp),
      verticalAlignment = Alignment.Top,
      horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {

      Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxHeight(),
      ) {
        Text(title, style = MedTrackerTheme.typography.headline)

        Spacer(modifier = Modifier.weight(1f))

        Text(dosage, style = MedTrackerTheme.typography.body)
        Text(info, style = MedTrackerTheme.typography.body)
      }

      Spacer(modifier = Modifier.weight(1f))

      Column(Modifier.fillMaxHeight(), horizontalAlignment = Alignment.End) {
        NavigationRow(
          onClick = onEditClick,
          label = "Edit",
        )

        FlyTextButton(
          errorButton = true,
          onClick = { onRemoveMedClick.invoke() },
          textStyle = MedTrackerTheme.typography.body
        ) {
          Text("Delete")
        }

      }

    }
  }
}