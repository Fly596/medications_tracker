package com.galeria.medicationstracker.ui.screens.profile.settings.profiledetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.ui.components.GBasicTextField
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

/* @OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDetailsScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Health Details") }, actions = {
                Text(
                    text = "Edit",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {  *//* Handle Edit Click *//*  }
                )
            })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(R.drawable.img_1543), // Replace with actual image resource
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(20.dp))
            HealthDetailItem2(label = "First Name", value = "Eugen")
            HealthDetailItem2(label = "Last Name", value = "Krylov")
            HealthDetailItem2(label = "Date of Birth", value = "Not Set")
            HealthDetailItem2(label = "Sex", value = "Not Set")
            HealthDetailItem2(label = "Blood Type", value = "Not Set")
            HealthDetailItem2(label = "Fitzpatrick Skin Type", value = "Not Set")
            HealthDetailItem2(label = "Wheelchair", value = "Not Set")
        }
    }
} */
@Composable
fun HealthDetailsScreen() {
    var firstName by remember { mutableStateOf("Eugen") }
    var lastName by remember { mutableStateOf("Krylov") }
    var dateOfBirth by remember { mutableStateOf("Not Set") }
    var sex by remember { mutableStateOf("Not Set") }
    var bloodType by remember { mutableStateOf("Not Set") }
    var fitzpatrickSkinType by remember { mutableStateOf("Not Set") }
    var wheelchair by remember { mutableStateOf("Not Set") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = {
                    // Handle back button click
                },
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Back",
                    tint = MedTrackerTheme.colors.sysBlack,
                    modifier = Modifier
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Health Details",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        Image(
            painter = painterResource(id = R.drawable.img_1543), // Replace with your drawable
            contentDescription = "Profile Icon",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .clip(CircleShape)
                .size(108.dp),
        )

        HealthDetailItem("First Name", firstName)
        HealthDetailItem("Last Name", lastName)
        HealthDetailItem("Date of Birth", dateOfBirth)
        HealthDetailItem("Sex", sex)
        HealthDetailItem("Blood Type", bloodType)
        HealthDetailItem("Fitzpatrick Skin Type", fitzpatrickSkinType)
        HealthDetailItem("Wheelchair", wheelchair)

        Text(
            text = "Track pushes instead of steps on Apple Watch in the Activity app, and in wheelchair workouts in the Workout app, and record them to Health. When this setting is on, your...",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
fun HealthDetailItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val interactionSource = remember { MutableInteractionSource() }
        GBasicTextField(
            value = value,
            onValueChange = { /* Handle value change */ },
            modifier = Modifier.fillMaxWidth(),
            interactionSource = interactionSource,
            prefix = label,
            prefixModifier = Modifier.padding(end = 16.dp)
        )
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDetailsScreenPreview() {
    MedTrackerTheme {
        HealthDetailsScreen()
    }
}
/*
@Composable
fun HealthDetailItem2(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Divider(color = Color.Gray.copy(alpha = 0.3f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            Text(text = value, fontSize = 16.sp, color = Color.Gray)
        }
    }
}
*/
