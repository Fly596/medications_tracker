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
import androidx.compose.material3.Scaffold
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
import com.galeria.medicationstracker.ui.components.FlyTopAppBar
import com.galeria.medicationstracker.ui.components.HIGListButton
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
  modifier: Modifier = Modifier,
  onSettingsClick: () -> Unit = {},
  onNotificationsClick: () -> Unit = {},
  viewModel: ProfileVM = viewModel(),
) {
  val state = viewModel.uiState

  Scaffold(
    topBar = {
      FlyTopAppBar("My Profile")
    },
    containerColor = MedTrackerTheme.colors.secondaryBackground,
    content = {
      Column(
        modifier = modifier
          .fillMaxSize()
          .padding(it)
          .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start

      ) {
        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        // Screen header with title.
        Row(
          modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top,
          horizontalArrangement = Arrangement.Start,
        ) {
          // User's profile picture and name.
          PfpWithName(
            // TODO: get from firebase.
            painter = R.drawable.img_1543,
            userName = "Adolf Hitler",
            userEmail = "fly.yt.77@gmail.com"
          )
          Spacer(modifier = Modifier.weight(1f))

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

        // Profile options.
        Column(
          modifier = Modifier.fillMaxWidth(),
          horizontalAlignment = Alignment.Start
        ) {

          // Notifications option.
          HIGListButton(
            onClick = { onNotificationsClick.invoke() },
            text = "Notifications Settings"
          )

          // Details option.
          HIGListButton(onClick = { onSettingsClick.invoke() }, text = "App Settings")

        }
        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        // Logout button.
        FlyTextButton(onClick = {}) {
          Text(text = "Log Out")
        }
      }
    }
  )


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
