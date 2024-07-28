package com.example.speechrecognitionapp.ui.screens.medication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.speechrecognitionapp.ui.screens.meds_list.MedsScreenViewModel

@Composable
fun MedicationScreen(
    viewModel: MedsScreenViewModel = viewModel(),
    modifier: Modifier = Modifier
) {

    val medication by viewModel.curMedication.collectAsStateWithLifecycle()

    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = "${medication?.name} - ${medication?.type}",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(name = "MedsScreen")
@Composable
private fun PreviewMedsScreen() {
    // MedicationsListScreen()
    }