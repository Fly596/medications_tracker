package com.galeria.medicationstracker.ui.screens.dashboard.record

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import androidx.lifecycle.compose.*
import androidx.lifecycle.viewmodel.compose.*
import com.galeria.medicationstracker.ui.componentsOld.*
import com.galeria.medicationstracker.ui.theme.*
import com.galeria.medicationstracker.utils.*

@Composable
fun IntakeRecordsScreen(
    modifier: Modifier = Modifier,
    viewModel: RecordsVM = viewModel(),
    onBackClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val intakes = uiState.intakes
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Displays the screen title.
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBackIosNew,
                    contentDescription = null,
                    tint = MedTrackerTheme.colors.primary400
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                "History",
                style = MedTrackerTheme.typography.display1Emphasized,
                modifier = Modifier.padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            
        }
        
        
        Spacer(modifier = Modifier.padding(8.dp))
        
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(intakes) { intake ->
                val formattedDate = if (intake.dateTime != null) {
                    formatTimestampTillTheDay(intake.dateTime)
                } else {
                    ""
                }
                LogsCard(
                    intake.medicationName.toString(),
                    intake.status.toString(),
                    date = formattedDate
                )
            }
        }
    }
}

@Composable
fun LogsCard(name: String, status: String, date: String) {
    FlySimpleCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.Top
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = name,
                    style = MedTrackerTheme.typography.bodyMediumEmphasized,
                    color = MedTrackerTheme.colors.primaryLabel
                )
                Text(
                    text = (if (status == "true") "Taken" else "Not Taken"),
                    style = MedTrackerTheme.typography.title2,
                    color = MedTrackerTheme.colors.secondaryLabel
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Column {
                Text(
                    text = date,
                    style = MedTrackerTheme.typography.title2,
                    color = MedTrackerTheme.colors.primaryLabel
                )
            }
        }
    }
}

