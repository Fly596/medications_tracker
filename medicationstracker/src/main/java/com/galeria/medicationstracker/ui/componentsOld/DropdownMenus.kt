package com.galeria.medicationstracker.ui.componentsOld

import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*

@Composable
fun FlyDropdownMenu(
  // onDismissRequest: () -> Unit,
  items: List<String>,
  selectedItem: String,
  onItemClick: (String) -> Unit,
) {
  var expanded by remember { mutableStateOf(false) }
  
  IconButton(onClick = { expanded = !expanded }) {
    Icon(Icons.Default.MoreVert, contentDescription = "More options")
  }
  
  DropdownMenu(
    expanded = expanded,
    onDismissRequest = { expanded = false },
    
    ) {
    items.forEach { option ->
      DropdownMenuItem(
        text = { Text(option) },
        onClick = {
          onItemClick(option)
          expanded = false
        }
      )
      
    }
  }
}