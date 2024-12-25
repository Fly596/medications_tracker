package com.galeria.medicationstracker.ui.screens.dashboard.record

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.model.formatTimestampTillTheDay
import com.galeria.medicationstracker.ui.components.FlySimpleCard
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

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
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBackIosNew,
                    contentDescription = null,
                    tint = MedTrackerTheme.colors.primary400
                )
            }
            Text(
                "History",
                style = MedTrackerTheme.typography.largeTitleEmphasized,
                modifier = Modifier.padding(start = 8.dp)
            )
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
                    style = MedTrackerTheme.typography.bodyEmphasized,
                    color = MedTrackerTheme.colors.primaryLabel
                )
                Text(
                    text = (if (status == "true") "Taken" else "Not Taken"),
                    style = MedTrackerTheme.typography.subhead,
                    color = MedTrackerTheme.colors.secondaryLabel
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Column {
                Text(
                    text = date,
                    style = MedTrackerTheme.typography.subhead,
                    color = MedTrackerTheme.colors.primaryLabel
                )
            }

        }
    }
}

