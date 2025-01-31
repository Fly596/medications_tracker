package com.galeria.medicationstracker.tests

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import com.galeria.medicationstracker.data.*
import com.galeria.medicationstracker.ui.componentsOld.HeaderWithIconButtonAndTitle
import com.galeria.medicationstracker.ui.theme.*

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