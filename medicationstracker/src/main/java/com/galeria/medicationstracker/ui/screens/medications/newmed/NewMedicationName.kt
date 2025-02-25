package com.galeria.medicationstracker.ui.screens.medications.newmed

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme


/*
@Composable
fun NewMedicationName(
    modifier: Modifier = Modifier,
    viewModel: NewMedViewModelNew = hiltViewModel(),
    onConfirm: () -> Unit = {},
    onNavigateBack: () -> Unit = {},
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    
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
*/

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun FlyElevatedCardMedsListPreview() {
    MedTrackerTheme {
/*         NewMedicationName(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 32.dp)
        ) */
    }
}