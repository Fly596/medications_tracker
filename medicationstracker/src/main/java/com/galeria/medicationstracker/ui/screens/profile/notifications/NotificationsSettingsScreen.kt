package com.galeria.medicationstracker.ui.screens.profile.notifications

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.*
import com.galeria.medicationstracker.ui.componentsOld.*
import com.galeria.medicationstracker.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsSettingsScreen(
  modifier: Modifier = Modifier,
  viewModel: NotificationsSettingsVM = viewModel()
) {

  Column(
    modifier = modifier
      .fillMaxSize(),
    //.padding(it),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {

    Spacer(modifier = Modifier.padding(8.dp))

    HIGListButton(
      onClick = { /* TODO: Navigates to screen. */ },
      text = "Med alarm sound",
      rightText = "Energy",
      textStyle = MedTrackerTheme.typography.bodyEmphasized
    )

    HIGListButton(
      onClick = { /* TODO: Navigates to screen. */ },
      text = "Customize notification",
      rightText = "Compact",
      textStyle = MedTrackerTheme.typography.bodyEmphasized
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