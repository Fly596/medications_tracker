package com.example.speechrecognitionapp.ui.screens.meds_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.speechrecognitionapp.R
import com.example.speechrecognitionapp.ui.shared.components.ButtonComponent
import com.example.speechrecognitionapp.ui.shared.components.CardComponent

@Composable
fun MedsListScreen(
    viewModel: MedsScreenViewModel = viewModel(),
    modifier: Modifier = Modifier
) {

    val medicationsData by viewModel.medications.collectAsStateWithLifecycle()
    val curName by viewModel.curMedication.collectAsStateWithLifecycle()

    Scaffold { innerPadding ->
        LazyColumn(
            modifier = modifier
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
/*             items(medicationsData) { medication ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = medication.name,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = medication.type,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            } */


            items(medicationsData) { medication ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    if (curName != null) {

                        Text(text = "${curName?.name}")
                    }

                    CardComponent(
                        // Можно получать имя выбранного medication.
                        header = medication.name,
                        topEndText = "Edit",
                        content = medication.type,
                        onClick = {
                            // TODO: Реализовать открытие экрана с выбранным medication.
                            viewModel.getMedicationByName(medication.name)
                        }
                    )

                }
            }

            item() {
                ButtonComponent(
                    text = R.string.button_add_medication,
                    onClick = {
                        viewModel.addMedication()
                    }
                )
                ButtonComponent(
                    text = R.string.button_update_medication,
                    onClick = {
                        viewModel.updateMedication()
                    }
                )
            }
        }


    }

}


@Preview(name = "MedsScreen")
@Composable
private fun PreviewMedsScreen() {
    // MedicationsListScreen()
}