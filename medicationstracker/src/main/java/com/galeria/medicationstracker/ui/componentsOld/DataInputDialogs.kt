package com.galeria.medicationstracker.ui.componentsOld

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.*
import androidx.lifecycle.compose.*
import androidx.lifecycle.viewmodel.compose.*
import com.galeria.medicationstracker.ui.theme.*

@Composable
fun InputDialog(
    modifier: Modifier = Modifier, title: String = "Title",
    viewModel: InputDialogViewModel = viewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    FlySimpleCard(
        isPrimaryBackground = false
    ) {

        // cancel/name/confirm.
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FlyTextButton(
                onClick = {/* TODO */ }
            ) {
                Text(
                    text = "Cancel",
                    style = MedTrackerTheme.typography.body
                )
            }

            Text(text = title, style = MedTrackerTheme.typography.title2Emphasized)

            FlyTextButton(
                onClick = {/* TODO */ }
            ) {
                Text(
                    text = "Add",
                    style = MedTrackerTheme.typography.bodyEmphasized
                )
            }

        }

        MyTextField(
            value = uiState.value.dateTimeInput.toString(),
            onValueChange = {/*
            TODO: Add date picker.
             */
            },
            label = "Date",
            modifier = Modifier.fillMaxWidth()
        )

        MyTextField(
            value = uiState.value.measurementInput,
            onValueChange = {/* TODO */ },
            label = title,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun InputDialogPreview() {
    MedTrackerTheme {
        InputDialog(modifier = Modifier)
    }
}