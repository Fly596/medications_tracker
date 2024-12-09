package com.galeria.medicationstracker.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.AutoMirrored
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.galeria.medicationstracker.data.UserMedication
import com.galeria.medicationstracker.ui.screens.medications.SimpleCardComponent
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

@Composable
fun FlyElevatedCard(
  modifier: Modifier = Modifier,
  icon: ImageVector = Icons.Filled.Medication,
  medication: UserMedication? = null,
  shape: Shape = RoundedCornerShape(8.dp),
  elevation: CardElevation = CardDefaults.elevatedCardElevation(),
  content: @Composable (ColumnScope.() -> Unit)
) {
  ElevatedCard(
    modifier = modifier
      .fillMaxWidth()
      .height(120.dp),
    shape = RoundedCornerShape(16.dp),
    elevation = CardDefaults.elevatedCardElevation(
      defaultElevation = 6.dp,
      pressedElevation = 8.dp,
      focusedElevation = 10.dp,
    ),
    colors =
      CardDefaults.elevatedCardColors(
        containerColor = MedTrackerTheme.colors.primaryBackgroundGrouped,
        contentColor = MedTrackerTheme.colors.primaryLabel,
      )
  ) {
    Row(
      modifier = Modifier
        .padding(16.dp)
        .fillMaxSize(),
      verticalAlignment = Alignment.Top,
      horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
      IconButton(onClick = { /*TODO*/ }, modifier = Modifier.size(56.dp)) {
        Icon(
          imageVector = icon,
          contentDescription = "Android Icon",
          modifier = Modifier.size(32.dp)
        )
      }

      Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxHeight(),
      ) {
        Text("Medicine Name", style = MedTrackerTheme.typography.headline)
        Text("Dosage: 500 mg", style = MedTrackerTheme.typography.body)
        Text("Frequency: Once a day", style = MedTrackerTheme.typography.body)
      }

      Spacer(modifier = Modifier.weight(1f))

      Column {
        IconButton(onClick = { /*TODO*/ }) {
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
            contentDescription = "Android Icon",
            modifier = Modifier.size(24.dp)
          )
        }
        Spacer(modifier = Modifier.weight(1f))

      }

    }
  }
}

@Preview(
  showSystemUi = false
)
@Composable
fun CardPreview() {
  MedTrackerTheme {
    Column(
      modifier = Modifier
        //.fillMaxSize()
        .padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      FlyElevatedCard(content = {

        Text(
          text = "Medicine Name: Aspirin",
          style = MedTrackerTheme.typography.body,
          modifier = Modifier.padding(8.dp)
        )

        Text(
          text = "Dosage: 500 mg",
          style = MedTrackerTheme.typography.body,
          modifier = Modifier.padding(8.dp)
        )
        Text(
          text = "Frequency: Once a day",
          style = MedTrackerTheme.typography.body,
          modifier = Modifier.padding(8.dp)
        )

      })
    }

  }

}