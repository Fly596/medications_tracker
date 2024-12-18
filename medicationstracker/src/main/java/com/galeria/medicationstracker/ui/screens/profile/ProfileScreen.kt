package com.galeria.medicationstracker.ui.screens.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.layout.*
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.*
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.ui.components.*
import com.galeria.medicationstracker.ui.theme.*

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
      .fillMaxSize(),
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
        userName = "Gerald Earl Gillum",
        userEmail = "fly.yt.77@gmail.com"
      )
      Spacer(modifier = Modifier.weight(1f))
      
      // Edit profile button.
      /*       IconButton(
              onClick = { *//*TODO: open settings*//*  }
      ) {
        Icon(
          modifier = Modifier.size(28.dp),
          imageVector = Icons.Filled.Edit,
          contentDescription = "Settings",
          tint = MedTrackerTheme.colors.secondaryLabel
        )
      } */
      
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
    modifier = modifier.fillMaxWidth(),
    verticalAlignment = Alignment.Top,
    horizontalArrangement = Arrangement.SpaceBetween,
  ) {
    Image(
      painter = painterResource(painter),
      contentDescription = "pfp",
      contentScale = ContentScale.Crop,
      modifier = modifier
        .clip(CircleShape)
        .size(128.dp),
    )
    
    // Spacer(modifier = Modifier.width(32.dp)) // add default dp values.
    
    Column(
      verticalArrangement = Arrangement.spacedBy(12.dp),
      modifier = Modifier.padding(top = 8.dp)
    ) {
      Text(
        text = userName,
        style = MedTrackerTheme.typography.title2Emphasized,
        color = MedTrackerTheme.colors.primaryLabel
      )
      Text(
        text = userEmail,
        style = MedTrackerTheme.typography.headline,
        color = MedTrackerTheme.colors.tertiaryLabel
      )
    }
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
