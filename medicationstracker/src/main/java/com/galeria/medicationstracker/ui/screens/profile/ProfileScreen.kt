package com.galeria.medicationstracker.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import com.galeria.medicationstracker.ui.components.HIGButton
import com.galeria.medicationstracker.ui.components.HIGButtonStyle
import com.galeria.medicationstracker.ui.components.HIGListButton
import com.galeria.medicationstracker.ui.screens.medications.update.UpdateMedVM
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

@Composable
fun ProfileScreen(
  modifier: Modifier = Modifier,
  viewModel: ProfileVM = viewModel(),
) {
  val state = viewModel.uiState

  Column(
    modifier = modifier.fillMaxSize(),
    verticalArrangement = Arrangement.spacedBy(8.dp),
    // horizontalAlignment = Alignment.CenterHorizontally,
  ) {


    // Screen header with title
    Text(
      "My Profile",
      style = MedTrackerTheme.typography.largeTitleEmphasized,
    )

    // Space between header and profile info.
    Spacer(modifier = Modifier.padding(8.dp))

    // User's profile picture and name.
    PfpWithName(
      // TODO: get from firebase.
      painter = R.drawable.img_1543,
      userName = "Adolf Hitler",
      userEmail = "fly.yt.77@gmail.com"
    )
    // Space between profile info and options.
    Spacer(modifier = Modifier.height(24.dp))

    // Profile options.
    Column(
      modifier = Modifier.fillMaxWidth(),
      horizontalAlignment = Alignment.Start
    ) {

      // Notifications option.
      ProfileOptionItem("Notifications") {
        // TODO: navigate to notifications screen.
      }
      // Details option.
      ProfileOptionItem("Settings") {
        // TODO: navigate to details screen.
      }
    }

    // Logout button.
    FlyTextButton(onClick = {}) {
      Text(text = "Logout")
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
    // horizontalAlignment = Alignment.CenterHorizontally,
    // verticalArrangement = Arrangement.Top,
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

    Spacer(modifier = Modifier.width(16.dp)) // add default dp values.

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
      Text(
        text = userName,
        style = MedTrackerTheme.typography.title1,
        color = MedTrackerTheme.colors.primaryLabel
      )
      Text(
        text = userEmail,
        style = MedTrackerTheme.typography.headline,
        color = MedTrackerTheme.colors.secondaryLabel
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
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
  ) {
    HIGListButton(onClick = onClick, text = title)
  }
}

@Preview(name = "ProfileScreen", showSystemUi = true, device = "id:pixel_8")
@Composable
private fun PreviewProfileScreen() {
  ProfileScreen()
}
