package com.galeria.medicationstracker.ui.screens.profile.notes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.galeria.medicationstracker.ui.components.GBasicTextField
import com.galeria.medicationstracker.ui.components.GOutlinedButton
import com.galeria.medicationstracker.ui.components.GPrimaryButton

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NewNoteScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    viewModel: NewNoteViewModel = hiltViewModel(),
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        GOutlinedButton(
            onBackClick
        ) {
            Text("Back")
        }
        GBasicTextField(
            value = state.value.title,
            onValueChange = {
                viewModel.updateTitle(it)
            },
            modifier = Modifier,
            interactionSource = interactionSource,
            prefix = "Title",
            prefixModifier = Modifier
        )
        // HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
        TextField(
            value = state.value.content,
            onValueChange = {
                viewModel.updateContent(it)
            },
            modifier = Modifier,
            label = { Text("Note Content") }
        )

        // Medication Chips
        Text(
            text = "Medications",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            state.value.medications.forEach { medication ->
                FilterChip(
                    text = medication.name.toString(),
                    isSelected = medication.name.toString() in state.value.selectedMedications,
                    onSelected = { viewModel.toggleMedication(medication.name.toString()) }
                )
            }
        }
        // Display selected medications
        if (state.value.selectedMedications.isNotEmpty()) {
            Text(
                text = "Selected: ${state.value.selectedMedications.joinToString()}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        GPrimaryButton(
            onClick = {
                viewModel.saveNote()
            }
        ) {
            Text("Save")
        }
    }
}

// Reusable FilterChip composable
@Composable
fun FilterChip(
    text: String,
    isSelected: Boolean,
    onSelected: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = modifier.clickable { onSelected(!isSelected) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Selected",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(4.dp))
            }
            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall,
                color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}