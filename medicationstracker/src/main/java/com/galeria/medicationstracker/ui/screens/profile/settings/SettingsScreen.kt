package com.galeria.medicationstracker.ui.screens.profile.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.vector.*
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.*
import com.galeria.medicationstracker.ui.componentsOld.*
import com.galeria.medicationstracker.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
  modifier: Modifier = Modifier,
  viewModel: SettingsViewModel = viewModel()
) {
  Column(
    modifier = modifier
      .fillMaxSize(),
    //.padding(it),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    Spacer(modifier = Modifier.padding(8.dp))

    HIGListButton(
      text = "Adjust Design",
      onClick = {
        // TODO: Navigates to screen.
      },
      leftIcon = Icons.Filled.Visibility
    )

    HIGListButton(
      text = "Select Start Page",
      onClick = {
        // TODO: Navigates to screen.
      },
      leftIcon = Icons.Filled.Start
    )

    HIGListButton(
      text = "Change Language",
      onClick = {
        // TODO: Navigates to screen.
      },
      leftIcon = Icons.Filled.Language
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