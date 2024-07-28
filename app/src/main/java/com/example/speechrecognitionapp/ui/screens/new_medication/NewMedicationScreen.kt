package com.example.speechrecognitionapp.ui.screens.new_medication

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NewMedicationScreen(
    viewModel: NewMedicationViewModel = viewModel(),
) {
    Column {
        TextField(
            value = viewModel.medName,
            onValueChange = { newValue ->
                viewModel.updateMedName(newValue)
            },
            label = {Text("Enter medication name")}
        )

        TextField(
            value = viewModel.medType,
            onValueChange = { newValue ->
                viewModel.updateMedType(newValue)
            },
            label = {Text("Enter medication type")}
        )
    }
}

