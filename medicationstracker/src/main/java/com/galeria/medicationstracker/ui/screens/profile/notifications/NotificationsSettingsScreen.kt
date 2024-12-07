package com.galeria.medicationstracker.ui.screens.profile.notifications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.ui.screens.medications.NavigationRow
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

@Composable
fun NotificationsSettingsScreen(
  modifier: Modifier = Modifier,
  viewModel: NotificationsSettingsVM = viewModel()
) {

  Column(
    modifier = modifier.fillMaxSize(),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {

    // Displays the screen title.
    Text(
      "Ringtone Settings",
      style = MedTrackerTheme.typography.largeTitle,
    )
    Spacer(modifier = Modifier.padding(8.dp))

    NotificationsSettingsCardComponent(
      title = "Med alarm sound",
      label = "Energy", // TODO: Replace with sound name.
      onClick = {
        // TODO: Navigates to screen.
      },
    )
    NotificationsSettingsCardComponent(
      title = "Customize notification",
      label = "Compact", // TODO: Replace with sound name.
      onClick = {
        // TODO: Navigates to screen.
      },
    )
  }
}

@Composable
fun NotificationsSettingsCardComponent(
  modifier: Modifier = Modifier,
  title: String = "category name",
  label: String = "sound name",
  onClick: () -> Unit,
) {

  ElevatedCard(modifier = modifier.padding(vertical = 4.dp)) {

    Row(
      modifier =
        Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
    ) {

      Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(title, style = MedTrackerTheme.typography.body)

        Spacer(modifier.weight(1f))

        NavigationRow(
          onClick = onClick,
          label = label
        )
      }
      // Text(description, style = MedTrackerTheme.typography.subhead)
    }
  }

}
@Preview
@Composable
fun NotificationsSettingsScreenPreview() {
  MedTrackerTheme {
    Surface {
      NotificationsSettingsScreen()
    }
  }
}
@Preview
@Composable
fun NotificationsSettingsCardComponentPreview() {
  MedTrackerTheme {
    Surface {
      NotificationsSettingsCardComponent(onClick = { })
    }
  }
}