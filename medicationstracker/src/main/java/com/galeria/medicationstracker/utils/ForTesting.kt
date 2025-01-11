package com.galeria.medicationstracker.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.galeria.medicationstracker.data.MedicationForms
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

@Composable
fun AddNewMedicationScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        // Header.
        HeaderWithIconButtonAndTitle(
            {/* TODO: Go Back */ },
            "New Medication"
        )

        Spacer(modifier = Modifier.padding(8.dp))

        // Type selection.
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                "Type",
                style = MedTrackerTheme.typography.title3Emphasized,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            LongBasicDropdownMenu(
                items = MedicationForms.entries.map {
                    it.name.lowercase().replaceFirstChar { it.uppercase() }
                },
                // items = listOf("Tablet", "Capsule", "Syrup", "Injection"),
            )

        }
    }
}

@Composable
fun LongBasicDropdownMenu(
    modifier: Modifier = Modifier,
    items: List<String> = emptyList(),
    onSelect: (String) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(true) }
    // Placeholder list of 100 strings for demonstration
    val menuItemData = List(6) { "Option ${it + 1}" }

    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        IconButton(
            onClick = { expanded = !expanded }) {
            Icon(Icons.Default.MoreVert, contentDescription = "More options")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = { /* Do something... */ }
                )
            }
        }
    }
}

@Composable
fun HeaderWithIconButtonAndTitle(
    onBackClick: () -> Unit,
    title: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { onBackClick.invoke() }
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBackIosNew,
                contentDescription = null,
                tint = MedTrackerTheme.colors.primary400
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        Text(
            title,
            style = MedTrackerTheme.typography.headline
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(name = "Fortesting")
@Composable
private fun PreviewFortesting() {
    MedTrackerTheme {
        AddNewMedicationScreen(
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }

}