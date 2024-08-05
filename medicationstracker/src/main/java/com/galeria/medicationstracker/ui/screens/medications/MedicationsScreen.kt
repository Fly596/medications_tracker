package com.galeria.medicationstracker.ui.screens.medications

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.galeria.medicationstracker.R

@Composable
fun MedicationsScreen(viewModel: MedicationsViewModel, modifier: Modifier = Modifier) {
    val medications by viewModel.userMedications.collectAsStateWithLifecycle()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        items(medications) { medication ->
            CardComponent(
                header = medication.name,
                topEndText = "Edit",
                content = medication.type,
                onClick = {
                    // TODO: Реализовать открытие экрана с выбранным medication.
                })
        }
    }
}

@Composable
fun CardComponent(
    header: String,
    topEndText: String?,
    content: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(modifier = modifier) {
        Column(
            modifier =
                Modifier.padding(
                    horizontal = dimensionResource(R.dimen.card_padding_horizontal),
                    vertical = dimensionResource(R.dimen.card_padding_vertical)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(header)
                    Spacer(modifier.weight(1f))
                    NavigationRow({ onClick() }, topEndText ?: "")
                }
                Text(content, style = MaterialTheme.typography.headlineMedium)
            }
    }
}

@Composable
fun NavigationRow(onClick: () -> Unit, label: String) {
    Row(
        modifier = Modifier.clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(label, style = MaterialTheme.typography.labelSmall)
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(10.dp))
        }
}
