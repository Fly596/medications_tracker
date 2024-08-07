package com.galeria.medicationstracker.ui.screens.new_medication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.galeria.medicationstracker.data.UserMedication
import com.galeria.medicationstracker.ui.screens.autentification.login.MyTextField
import com.galeria.medicationstracker.ui.theme.MedicationsTrackerAppTheme

@Composable
fun NewMedicationScreen(
    viewModel: NewMedicationViewModel,
    modifier: Modifier = Modifier
) {
    var inputName by remember { mutableStateOf("") }
    var inputForm by remember { mutableStateOf("") }
    var inputStrength by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("") }
    var inputFrequency by remember { mutableStateOf("") }
    var inputTime by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarState by viewModel.snackbarState.collectAsState()

    var newMed: UserMedication

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            MyTextField(
                value = inputName,
                onValueChange = { inputName = it },
                label = "Name",
                modifier = Modifier.fillMaxWidth(),
            )
            MyTextField(
                value = inputForm,
                onValueChange = { inputForm = it },
                label = "Form",
                modifier = Modifier.fillMaxWidth(),
            )

            MyTextField(
                value = inputStrength,
                onValueChange = { inputStrength = it },
                label = "Strength",
                modifier = Modifier.fillMaxWidth(),
            )

            MyTextField(
                value = inputUnit,
                onValueChange = { inputUnit = it },
                label = "Unit",
                modifier = Modifier.fillMaxWidth(),
            )
            MyTextField(
                value = inputFrequency,
                onValueChange = { inputFrequency = it },
                label = "Frequency",
                modifier = Modifier.fillMaxWidth(),
            )

            MyTextField(
                value = inputTime,
                onValueChange = { inputTime = it },
                label = "Time",
                modifier = Modifier.fillMaxWidth(),
            )

            Button(
                onClick = {
                    newMed = UserMedication(
                        name = inputName,
                        form = inputForm,
                        strength = inputStrength.toFloat(),
                        unit = inputUnit,
                        frequency = inputFrequency
                    )
                    viewModel.addMedication(
                        newMed
                    )
                },
                ) {
                Text("Add medication")
            }

        }
        LaunchedEffect(snackbarState.message) {
            snackbarState.message?.let { message ->
                snackbarHostState.showSnackbar(message)
            }
        }
    }


}

@Preview(
    showBackground = false,
    showSystemUi = true,
    device = "spec:parent=pixel_8,navigation=buttons"
)
@Composable
private fun PreviewNewMed() {
    val viewModel: NewMedicationViewModel = NewMedicationViewModel()
    MedicationsTrackerAppTheme {
        Column() {
            NewMedicationScreen(viewModel)
        }
    }
}