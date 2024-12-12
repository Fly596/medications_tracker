package com.galeria.medicationstracker.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.galeria.medicationstracker.data.UserMedication
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

@Composable
fun FlyElevatedCardDashboard(
  modifier: Modifier = Modifier,
  icon: ImageVector? = null,
  title: String = "Medicine Name",
  time: String = "9:00 AM",
  info: String = "Mon, Tue, Fri...",
  medication: UserMedication? = null,
  shape: Shape = RoundedCornerShape(8.dp),
  elevation: CardElevation = CardDefaults.elevatedCardElevation(),
  // content: @Composable (ColumnScope.() -> Unit)
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
      if (icon != null) {
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
        Text(title, style = MedTrackerTheme.typography.headline)
        Text(time, style = MedTrackerTheme.typography.body)
        Text(info, style = MedTrackerTheme.typography.body)
      }

      Spacer(modifier = Modifier.weight(1f))

      var isChecked by remember { mutableStateOf(false) } // State to track icon

      Column {
        IconButton(onClick = { isChecked = !isChecked }) {
          Icon(
            /*        if(isChecked){
                     imageVector =
                   } */
            imageVector = if (isChecked) {
              Icons.Filled.CheckCircle
            } else {
              Icons.Outlined.CheckCircle
            },
            contentDescription = "Android Icon",
            modifier = Modifier.size(32.dp),
            tint = if (isChecked) {
              MedTrackerTheme.colors.primary400
            } else {
              MedTrackerTheme.colors.separator
            }
          )
        }
        Spacer(modifier = Modifier.weight(1f))

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
  // content: @Composable (ColumnScope.() -> Unit)
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
          onClick = { onRemoveMedClick.invoke() } ,
          textStyle = MedTrackerTheme.typography.body
        ) {
          Text("Delete")
        }

      }

    }
  }
}

@Composable
fun NavigationRow(onClick: () -> Unit, label: String? = null) {
  Row(
    modifier = Modifier.clickable(onClick = onClick),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(4.dp),
  ) {
    FlyTextButton(
      onClick
    ) {
      if (label != null) {
        Text(label)
      }
      Icon(
        imageVector = Icons.Filled.Edit,
        contentDescription = null,
        tint = MedTrackerTheme.colors.secondary400,
        modifier = Modifier.padding(start = 8.dp),
      )
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
/*       FlyElevatedCard(content = {

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

      }) */
    }

  }

}