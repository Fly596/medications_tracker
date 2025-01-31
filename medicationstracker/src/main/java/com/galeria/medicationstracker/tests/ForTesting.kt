package com.galeria.medicationstracker.tests

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.galeria.medicationstracker.data.MedicationForm
import com.galeria.medicationstracker.ui.componentsOld.HeaderWithIconButtonAndTitle
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

// region AddNewMedicationScreen
@Composable
fun AddNewMedicationScreen(
    modifier: Modifier = Modifier
) {
    val medicationForm =
        MedicationForm.entries.map {
            it.name.lowercase().replaceFirstChar { it.uppercase() }
        }
    
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
            /*          LargeDropdownMenu(
                         medicationForms
                     )
                     { selectedItem ->
                         println("Selected: $selectedItem")
                     } */
            
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LargeDropdownMenu(
    items: List<String>,
    label: String = "",
    onItemSelected: (String) -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }
    
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        // TextField to show the selected item
        TextField(
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor() // Ensures alignment with dropdown
        )
        
        // Dropdown menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        selectedText = item
                        expanded = false
                        onItemSelected(item)
                    }
                )
            }
        }
    }
}



// endregion

@Preview(name = "Fortesting")
@Composable
private fun PreviewFortesting() {
    MedTrackerTheme {
        
   
    }
    
}