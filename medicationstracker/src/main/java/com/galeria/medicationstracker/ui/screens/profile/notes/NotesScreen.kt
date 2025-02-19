package com.galeria.medicationstracker.ui.screens.profile.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.galeria.medicationstracker.ui.componentsOld.FlySimpleCard
import com.galeria.medicationstracker.ui.componentsOld.MyTextField
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

@Composable
fun NotesScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    viewModel: NotesScreenViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // title and "edit" button.
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Notes",
                style = MedTrackerTheme.typography.display3Emphasized
            )
            IconButton(
                modifier = Modifier,
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "new note",
                    tint = MedTrackerTheme.colors.primaryLabel,
                    modifier = Modifier.size(36.dp)
                )
            }
        }
        
        LazyColumn(
        ) {
            items(4) {
                UserNoteCard()
            }
        }
    }
}

@Composable
fun UserNoteCard() {
    var tfVal by remember { mutableStateOf("content") }
    FlySimpleCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
        ) {
            Text(text = "Feb 18, 2025", style = MedTrackerTheme.typography.title3)
            Text(text = "NoteTitle", style = MedTrackerTheme.typography.title1Emphasized)
            Spacer(modifier = Modifier.height(4.dp))
            MyTextField(
                modifier = Modifier.fillMaxWidth(),
                value = tfVal,
                onValueChange = { tfVal = it },
                label = "Label",
                maxLines = 3
            )
        }
    }
    
}

@Composable
@Preview(showSystemUi = false, showBackground = true, device = "id:pixel_8")
fun NotesScreenPreview() {
    MedTrackerTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            NotesScreen()
        }
    }
}