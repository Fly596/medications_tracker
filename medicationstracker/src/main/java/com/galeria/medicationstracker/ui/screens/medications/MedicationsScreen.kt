package com.galeria.medicationstracker.ui.screens.medications

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import androidx.lifecycle.compose.*
import androidx.lifecycle.viewmodel.compose.*
import com.galeria.medicationstracker.data.*
import com.galeria.medicationstracker.ui.componentsOld.*
import com.galeria.medicationstracker.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicationsScreen(
    modifier: Modifier = Modifier,
    onAddMedClick: () -> Unit = {},
    onViewMed: () -> Unit,
    onEditMedClick: (String) -> Unit = {},
    medicationsViewModel: MedicationsViewModel = viewModel(),
    medsPagesVM: MedsPagesViewModel = viewModel(),
) {
    
    val uiState by medicationsViewModel.uiState.collectAsStateWithLifecycle()
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            
            items(uiState.userMedications) { med ->
                FlyElevatedCardMedsList(
                    title = med.name.toString(),
                    dosage = ("${med.strength} ${
                        med.unit.toString().lowercase()
                    }"),
                    info = med.form.toString().lowercase(),
                    onEditClick = { onEditMedClick(med.name.toString()) },
                    onRemoveMedClick = {
                        medicationsViewModel.deleteMedicationFromFirestore(med.name.toString())
                    },
                    onViewMed = {
                        medsPagesVM.getSelectedMed(med.name.toString())
                        onViewMed()
                    }
                )
            }
            
            item {
                // Button to add a new medication.
                FlyButton(
                    onClick = {
                        onAddMedClick.invoke()
                        /*                         try {
                            onAddMedClick.invoke()
                        } catch (e: Exception) {
                            println("Error in medication screen: $e")
                        } */
                    },
                    Modifier.fillMaxWidth()
                ) {
                    Text("+ Add Medication")
                }
            }
        }
        
    }
}

@Composable
fun FlyElevatedCardMedsList(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    title: String = "Medicine Name",
    dosage: String = "50 mg",
    info: String = "Mon, Tue, Fri...",
    onEditClick: () -> Unit,
    onRemoveMedClick: () -> Unit,
    onViewMed: () -> Unit,
    medication: UserMedication? = null,
    shape: Shape = RoundedCornerShape(8.dp),
    elevation: CardElevation = CardDefaults.elevatedCardElevation(),
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onViewMed.invoke()
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 0.dp,
            pressedElevation = 8.dp,
            focusedElevation = 10.dp,
        ),
        colors =
            CardDefaults.elevatedCardColors(
                containerColor = MedTrackerTheme.colors.primaryBackground,
                contentColor = MedTrackerTheme.colors.primaryLabel,
                disabledContainerColor = MedTrackerTheme.colors.primaryTinted,
                disabledContentColor = MedTrackerTheme.colors.secondary600
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxHeight(),
            ) {
                Text(title, style = MedTrackerTheme.typography.headline)
                
                Spacer(modifier = Modifier.weight(1f))
                
                Text(dosage, style = MedTrackerTheme.typography.bodyMedium)
                Text(info, style = MedTrackerTheme.typography.bodyMedium)
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            Column(Modifier, horizontalAlignment = Alignment.End) {
                NavigationRow(
                    onClick = onEditClick,
                    label = "Edit",
                )
                
                FlyTextButton(
                    errorButton = true,
                    onClick = { onRemoveMedClick.invoke() },
                    textStyle = MedTrackerTheme.typography.labelLargeEmphasized
                ) {
                    Text("Delete")
                }
                
            }
            
        }
    }
}

@Preview(backgroundColor = 0xFFF1F1F1, showBackground = true)
@Composable
fun FlyElevatedCardMedsListPreview() {
    MedTrackerTheme {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            FlyElevatedCardMedsList(
                onEditClick = { /*TODO*/ },
                onRemoveMedClick = { /*TODO*/ },
                onViewMed = { /*TODO*/ }
            )
        }
        
    }
}