package com.galeria.medicationstracker.ui.screens.profile.notifications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.ui.componentsOld.HIGListButton
import com.galeria.medicationstracker.ui.componentsOld.NavigationRow
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

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
      textStyle = MedTrackerTheme.typography.bodyMediumEmphasized
    )

    HIGListButton(
      onClick = { /* TODO: Navigates to screen. */ },
      text = "Customize notification",
      rightText = "Compact",
      textStyle = MedTrackerTheme.typography.bodyMediumEmphasized
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
        Text(title, style = MedTrackerTheme.typography.bodyMedium)

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