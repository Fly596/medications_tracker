package com.galeria.medicationstracker.ui.screens.profile


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.ui.shared.components.HIGButton
import com.galeria.medicationstracker.ui.shared.components.HIGButtonStyle
import com.galeria.medicationstracker.ui.theme.ExtendedColors
import com.galeria.medicationstracker.ui.theme.MedicationsTrackerAppTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Heading.
        Text(text = "Profile", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        PfpWithName(
            painter = R.drawable.img_1543, // TODO: get and set using firebase.
            userName = "User Name", // TODO: get from firebase.
        )

        Spacer(modifier = Modifier.height(24.dp))

        ProfileOptionItem("Details") {
            // TODO: navigate to details screen.
        }
        ProfileOptionItem("Notifications") {
            // TODO: navigate to notifications screen.
        }

        HIGButton(
            onClick = {},
            text = "Logout",
            style = HIGButtonStyle.Bezeled
            )

    }
}

@Composable
fun PfpWithName(
    painter: Int,
    userName: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(painter),
            contentDescription = "pfp",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .clip(CircleShape)
                .size(128.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = userName, style = MaterialTheme.typography.headlineLarge)
    }
}

@Composable
fun ProfileOptionItem(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(onClick = onClick) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = "Show options",
                tint = MedicationsTrackerAppTheme.colors.extGray.defaultLight
            )
        }
    }
}


@Preview(name = "ProfileScreen", showSystemUi = true, device = "id:pixel_8")
@Composable
private fun PreviewProfileScreen() {
    ProfileScreen()
}