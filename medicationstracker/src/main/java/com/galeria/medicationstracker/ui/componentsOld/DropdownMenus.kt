package com.galeria.medicationstracker.ui.componentsOld

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
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