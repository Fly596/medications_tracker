package com.galeria.medicationstracker.ui.screens.medications.mediinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

// Ui remake.
@Composable
fun MedicationScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TopBar()
        MedicationInfo()
        ScheduleSection()
        DetailsSection()
        ProgressSection()
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Medication",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        IconButton(onClick = { /* Handle back click */ }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More options"
            )
        }
    }
}

@Composable
fun MedicationInfo() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Active course",
            fontSize = 14.sp,
            color = Color.Gray
        )
        Text(
            text = "Roaccutane 30mg",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Isotretinoin, also known as 13-cis-retinoic acid, is primarily used to treat severe acne.",
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun ScheduleSection() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Schedule",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(
                modifier = Modifier
                    .background(
                        Color.LightGray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp)
            ) {
                Text(text = "07:00-09:00")
            }
            Box(
                modifier = Modifier
                    .background(
                        Color.LightGray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp)
            ) {
                Text(text = "18:00-20:00")
            }
        }
    }
}

@Composable
fun DetailsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MedTrackerTheme.colors.primaryBackground
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.pill_logo),
                contentDescription = "Pill Icon",
                modifier = Modifier.size(64.dp)
            )
            Text(
                text = "Capsules",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DetailBox(title = "Duration", content = "6 months")
            DetailBox(title = "Dose", content = "2/day")
            DetailBox(title = "Frequency", content = "Daily")
        }
    }

}

@Composable
fun DetailBox(title: String, content: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            style = MedTrackerTheme.typography.labelMedium,
        )
        Text(
            text = content, style = MedTrackerTheme.typography.bodyMediumEmphasized
        )
    }
}

@Composable
fun ProgressSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Progress",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                CircularProgressIndicator(
                    progress = { 0.4f },
                    trackColor = ProgressIndicatorDefaults.circularIndeterminateTrackColor,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "40% complete",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .background(Color(0xFF2196F3), shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Possible side effects",
                    fontSize = 16.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Learn more about this medication, its side effects, etc.",
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(name = "MedicationInfo")
@Composable
private fun PreviewMedicationInfo() {
    MedTrackerTheme {
        // MedicationScreen()
    }
    // MedicationInfo()
}