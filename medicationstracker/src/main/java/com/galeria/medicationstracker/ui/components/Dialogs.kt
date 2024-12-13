package com.galeria.medicationstracker.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun LogMedicationTimeDialog(
  onDismissRequest: () -> Unit,
  onConfirmation: () -> Unit,
) {
  val currentDate = LocalDateTime.now()
  val dateFormatter = DateTimeFormatter.ofPattern("MMM dd, hh:mm a")
  val formattedCurrentDate = currentDate.format(dateFormatter)

  Dialog(onDismissRequest = { onDismissRequest() }) {
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
            onConfirmation()
          },
          onSkipped = {
            onDismissRequest()
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
    // elevation = 4.dp
  ) {
    Column(
      // verticalArrangement = Arrangement.Top,
      modifier = Modifier.padding(16.dp)
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          imageVector = Icons.Default.Medication, // Replace with your icon resource
          contentDescription = "Capsule Icon",
          modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
          Text(
            text = medicationName,
            // style = MaterialTheme.typography.h6
          )
          Text(
            text = "$form, $strength",
            // style = MaterialTheme.typography.body2
          )
          Text(
            text = "1 capsule at $intakeTime",
            // style = MaterialTheme.typography.body2,
            // color = MaterialTheme.colors.primary
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
          // colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
        ) {
          Text(text = "Skipped")
        }
        FlyTonalButton(
          modifier = Modifier.weight(0.5f),
          onClick = onTaken,
          // colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
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
  LogMedicationTimeDialog(
    onDismissRequest = { /*TODO*/ },
    onConfirmation = { /*TODO*/ }
  )
}