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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.galeria.medicationstracker.data.MedicationUnit
import com.galeria.medicationstracker.ui.components.GRadioButtonsGroup
import com.galeria.medicationstracker.ui.componentsOld.MyTextField
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme


@Composable
fun NewMedicationScreen(
    // viewModel: NewMedicationViewModel,
    modifier: Modifier = Modifier,
    onConfirm: () -> Unit = {},
    onNavigateBack: () -> Unit = {},
) {
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
        
        MyTextField(
            value = "Name",
            onValueChange = {},
            label = "Name",
            isPrimaryColor = true,
            modifier = Modifier.fillMaxWidth()
        )
        
        MyTextField(
            value = "Strength",
            onValueChange = {},
            label = "Strength",
            isPrimaryColor = true,
            modifier = Modifier.fillMaxWidth()
        )
        
        val options = MedicationUnit.entries.toTypedArray()
        
 /*        val unitOptions: List<String> = listOf("1", "2")
        
        enumValues<MedicationUnit>().forEach {
            unitOptions.plus(it.toString())
        } */
        
        GRadioButtonsGroup(radioOptions = options)
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