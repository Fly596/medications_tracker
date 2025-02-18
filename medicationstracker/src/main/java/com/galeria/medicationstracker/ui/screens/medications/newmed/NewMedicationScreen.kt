package com.galeria.medicationstracker.ui.screens.medications.newmed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.data.MedicationForm
import com.galeria.medicationstracker.data.MedicationUnit
import com.galeria.medicationstracker.ui.components.GPrimaryButton
import com.galeria.medicationstracker.ui.components.GRadioButton
import com.galeria.medicationstracker.ui.components.GTextField
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme


@Composable
fun NewMedicationScreen(
    modifier: Modifier = Modifier,
    viewModel: NewMedicationViewModel = viewModel(),
    onConfirm: () -> Unit = {},
    onNavigateBack: () -> Unit = {},
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    // TODO: finish this shi.
    Column(modifier = modifier.fillMaxSize()) {
        // Title.
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            IconButton(
                onNavigateBack,
            ) {
                Icon(
                    Icons.Default.ArrowBackIosNew,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
            Text(
                text = "New Medication",
                style = MedTrackerTheme.typography.display3Emphasized,
            )
        }
        
        Spacer(Modifier.height(24.dp))
        
        GTextField(
            value = state.value.medName,
            onValueChange = { viewModel.updateName(it) },
            label = "Name",
            isPrimaryColor = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )
        
        GTextField(
            value = state.value.strength,
            onValueChange = { viewModel.updateStrength(it) },
            label = "Strength",
            isPrimaryColor = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )
        
        var selectedUnit by remember { mutableStateOf(state.value.unit) }
        val unitOptions = MedicationUnit.entries.toTypedArray()
        // Units.
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            unitOptions.forEach { unit ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = unit.toString().lowercase())
                    GRadioButton(
                        selected = selectedUnit == unit,
                        onClick = {
                            viewModel.updateUnit(selectedUnit)
                            selectedUnit = unit
                        })
                }
            }
        }
        
        var selectedForm by remember { mutableStateOf(state.value.form) }
        val formOptions = MedicationForm.entries.toTypedArray()
        // Form.
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            formOptions.forEach { form ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = form.toString().lowercase())
                    GRadioButton(
                        selected = selectedForm == form,
                        onClick = {
                            viewModel.updateForm(selectedForm)
                            selectedForm = form
                        })
                }
            }
        }
        
        //todo: dates
        
        
        GPrimaryButton(onClick = onConfirm, modifier = Modifier.fillMaxWidth()) {
            Text("Add")
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun FlyElevatedCardMedsListPreview() {
    MedTrackerTheme {
        NewMedicationScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 32.dp)
        )
    }
}