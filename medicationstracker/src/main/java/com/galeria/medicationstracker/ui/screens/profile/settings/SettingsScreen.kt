package com.galeria.medicationstracker.ui.screens.profile.settings

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
import androidx.compose.material.icons.filled.FirstPage
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Start
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.ui.screens.medications.NavigationRow
import com.galeria.medicationstracker.ui.screens.medications.SimpleCardComponent
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

@Composable
fun SettingsScreen(
  modifier: Modifier = Modifier,
  viewModel: SettingsViewModel = viewModel()
) {

  Column(
    modifier = modifier.fillMaxSize(),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {

    // Displays the screen title.
    Text(
      "Settings",
      style = MedTrackerTheme.typography.largeTitle,
    )
    Spacer(modifier = Modifier.padding(8.dp))

    SettingsCardComponent(
      icon = Icons.Filled.Visibility,
      title = "Adjust Design",
      onClick = {
        // TODO: Navigates to screen.
      },
    )

    SettingsCardComponent(
      icon = Icons.Filled.Start,
      title = "Select Start Page",
      onClick = {
        // TODO: Navigates to screen.
      },
    )


    SettingsCardComponent(
      icon = Icons.Filled.Language,
      title = "Change Language",
      onClick = {
        // TODO: Navigates to screen.
      },
    )


  }
}

@Composable
fun SettingsCardComponent(
  modifier: Modifier = Modifier,
  icon: ImageVector? = null,
  title: String = "category name",
  onClick: () -> Unit,
) {

  ElevatedCard(modifier = modifier.padding(vertical = 8.dp)) {
    Column(
      modifier =
        Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
      // verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
    ) {

      Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Row(
          horizontalArrangement = Arrangement.spacedBy(12.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          if (icon != null) {
            Icon(
              imageVector = icon,
              contentDescription = null,
              tint = MedTrackerTheme.colors.secondary600,
              modifier = Modifier.size(24.dp),
            )
          }

          Text(title, style = MedTrackerTheme.typography.body)

        }

        Spacer(modifier.weight(1f))

        Icon(
          imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
          contentDescription = null,
          tint = MedTrackerTheme.colors.separator,
          modifier = Modifier.size(24.dp),
        )
      }
      // Text(description, style = MedTrackerTheme.typography.subhead)
    }
  }

}