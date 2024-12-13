package com.galeria.medicationstracker.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.ui.components.FlyTextButton
import com.galeria.medicationstracker.ui.components.HIGListButton
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

/**
 * Represents the user's profile screen.
 *
 * This screen displays the user's profile information, including their
 * profile picture, name, and email. It also provides options for
 * managing notifications and app settings, as well as a logout button.
 *
 * @param modifier The modifier to be applied to the layout.
 * @param onSettingsClick A callback function that is invoked when the user clicks on the settings option.
 * @param onNotificationsClick A callback function that is invoked when the user clicks on the notifications option.
 * @param viewModel The ViewModel for this screen. Defaults to a new instance of ProfileVM.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
  modifier: Modifier = Modifier,
  onSettingsClick: () -> Unit = {},
  onNotificationsClick: () -> Unit = {},
  viewModel: ProfileVM = viewModel(),
) {
  val state = viewModel.uiState

  // Build the screen UI.
  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),
    horizontalAlignment = Alignment.Start

  ) {
    Spacer(modifier = Modifier.padding(vertical = 8.dp))

    // Display header with profile picture and name.
    Row(
      modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top,
      horizontalArrangement = Arrangement.Start,
    ) {

      // User's profile information.
      PfpWithName(
        // TODO: get from firebase.
        painter = R.drawable.img_1543,
        userName = "Adolf Hitler",
        userEmail = "fly.yt.77@gmail.com"
      )
      Spacer(modifier = Modifier.weight(1f))

      // Edit profile button.
      IconButton(
        onClick = {/*TODO: open settings*/ }
      ) {
        Icon(
          modifier = Modifier.size(28.dp),
          imageVector = Icons.Filled.Edit,
          contentDescription = "Settings",
          tint = MedTrackerTheme.colors.secondaryLabel
        )
      }

    }

    // Profile options section.
    Column(
      modifier = Modifier.fillMaxWidth(),
      horizontalAlignment = Alignment.Start
    ) {

      // Notifications settings.
      HIGListButton(
        onClick = { onNotificationsClick.invoke() },
        text = "Notifications Settings"
      )

      // App settings.
      HIGListButton(onClick = { onSettingsClick.invoke() }, text = "App Settings")

    }
    Spacer(modifier = Modifier.padding(vertical = 8.dp))

    // Logout button.
    FlyTextButton(onClick = {}) {
      Text(text = "Log Out")
    }
  }
}

@Composable
fun PfpWithName(
  painter: Int,
  userName: String,
  userEmail: String,
  modifier: Modifier = Modifier
) {

  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Start,
    modifier = modifier,
  ) {
    Image(
      painter = painterResource(painter),
      contentDescription = "pfp",
      contentScale = ContentScale.Crop,
      modifier = modifier
        .clip(CircleShape)
        .size(128.dp),
    )

    Spacer(modifier = Modifier.width(32.dp)) // add default dp values.

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
      Text(
        text = userName,
        style = MedTrackerTheme.typography.title1Emphasized,
        color = MedTrackerTheme.colors.primaryLabel
      )
      Text(
        text = userEmail,
        style = MedTrackerTheme.typography.headline,
        color = MedTrackerTheme.colors.tertiaryLabel
      )
    }

  }
}

@Composable
fun ProfileOptionItem(title: String, onClick: () -> Unit) {

  Row(
    modifier =
      Modifier
        .fillMaxWidth()
        // .background(color =
        // MedicationsTrackerAppTheme.systemColors.backgroundLightSecondary)
        .clickable(onClick = onClick),
  ) {
  }
}

@Preview(name = "ProfileScreen", showSystemUi = true, device = "id:pixel_8")
@Composable
private fun PreviewProfileScreen() {
  ProfileScreen()
}
