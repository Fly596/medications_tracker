package com.galeria.medicationstracker.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.*
import com.galeria.medicationstracker.ui.screens.dashboard.*
import com.galeria.medicationstracker.ui.theme.*
import java.time.*
import java.time.format.*

@Composable
fun LogMedicationTimeDialog(
    viewModel: DashboardVM,
    onDismiss: () -> Unit,
    onConfirmation: () -> Unit,
) {
  val currentDate = LocalDateTime.now()
  val dateFormatter = DateTimeFormatter.ofPattern("MMM dd, hh:mm a")
  val formattedCurrentDate = currentDate.format(dateFormatter)

  Dialog(onDismissRequest = { onDismiss() }) {
    // Draw a rectangle shape with rounded corners inside the dialog
    Card(
      modifier = Modifier
          .fillMaxWidth()
          // .height(375.dp)
          .padding(16.dp),
      colors = CardDefaults.elevatedCardColors(
        containerColor = MedTrackerTheme.colors.primaryBackgroundGrouped,
        contentColor = MedTrackerTheme.colors.primaryLabel,
      ),
      shape = RoundedCornerShape(16.dp),
    ) {
      Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Text(
          text = "$formattedCurrentDate",
          modifier = Modifier.padding(16.dp),
          style = MedTrackerTheme.typography.title2Emphasized
        )

        LogDialogMedicationCard(
          // TODO: Add logic for when the user takes the medication.
          onTaken = {
            onConfirmation.invoke()
          },
          onSkipped = {
            onDismiss.invoke()
          }
        )
        FlyButton(
          modifier = Modifier
              .fillMaxWidth()
              .padding(top = 16.dp),
          onClick = onConfirmation,
          // colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
        ) {
          Text("Done")
        }
      }
    }
  }
}

@Composable
fun LogDialogMedicationCard(
    medicationName: String = "Adderall",
    form: String = "Tablet",
    strength: String = "50.0",
    intakeTime: String = "2:00 PM",
    onSkipped: () -> Unit = {},
    onTaken: () -> Unit = {}
) {
  Card(
    modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp),
    colors = CardDefaults.elevatedCardColors(
      containerColor = MedTrackerTheme.colors.secondaryBackgroundGrouped,
      contentColor = MedTrackerTheme.colors.primaryLabel,
    )
  ) {
    Column(
      modifier = Modifier.padding(16.dp)
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          imageVector = Icons.Default.Medication,
          contentDescription = "Capsule Icon",
          modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
          Text(
            text = medicationName,
          )
          Text(
            text = "$form, $strength",
          )
          Text(
            text = "1 capsule at $intakeTime",
          )
        }
      }
      Spacer(modifier = Modifier.height(16.dp))
      Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        FlyTonalButton(
          modifier = Modifier.weight(0.5f),
          onClick = onSkipped,
        ) {
          Text(text = "Skipped")
        }
        FlyTonalButton(
          modifier = Modifier.weight(0.5f),
          onClick = onTaken,
        ) {
          Text(text = "Taken")
        }
      }
    }
  }
}

@Preview
@Composable
fun DialogWithImagePreview() {

}