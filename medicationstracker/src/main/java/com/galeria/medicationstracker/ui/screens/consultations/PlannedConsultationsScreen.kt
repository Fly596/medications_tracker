package com.galeria.medicationstracker.ui.screens.consultations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.galeria.medicationstracker.R
import com.galeria.medicationstracker.ui.components.FlyButton
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

@Composable
fun PlannedConsultationsScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        AppointmentCard(
            name = "John Doe",
            age = 30,
            date = "2023-10-25",
            time = "10:00 AM",
            onRejectClick = {},
            onDoneClick = {}
        )
    }
}

@Composable
fun AppointmentCard(
    name: String,
    age: Int,
    date: String,
    time: String,
    onRejectClick: () -> Unit,
    onDoneClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MedTrackerTheme.colors.primaryBackground,
            contentColor = MedTrackerTheme.colors.primaryLabel
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Row for avatar and name details
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            MedTrackerTheme.colors.secondaryBackground,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = name.first().toString(),
                        style = MedTrackerTheme.typography.bodyEmphasized,
                        color = MedTrackerTheme.colors.primaryLabel
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = name,
                        style = MedTrackerTheme.typography.bodyEmphasized,
                        color = MedTrackerTheme.colors.primaryLabel
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "$age years",
                        style = MedTrackerTheme.typography.caption1,
                        color = MedTrackerTheme.colors.secondaryLabel
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            // Date and Time row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painterResource(R.drawable.calendar_clock_outline),
                        modifier = Modifier.size(22.dp),
                        contentDescription = "Date",
                        tint = MedTrackerTheme.colors.secondaryLabel
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = date, style = MedTrackerTheme.typography.caption1)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = "Time",
                        tint = MedTrackerTheme.colors.secondaryLabel
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = time, fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            // Action buttons
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(
                    onClick = onRejectClick,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MedTrackerTheme.colors.sysError
                    )
                ) {
                    Text(text = "Reject")
                }

                FlyButton(
                    onClick = onDoneClick,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f),
                ) {
                    Text(text = "Done", color = Color.White)
                }
            }
        }
    }
}

@Preview(name = "PlannedConsultationsScreen")
@Composable
private fun PreviewPlannedConsultationsScreen() {
    MedTrackerTheme {
        PlannedConsultationsScreen()
    }
}