package com.galeria.medicationstracker.ui.doctor

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.lifecycle.compose.*
import androidx.lifecycle.viewmodel.compose.*
import com.galeria.medicationstracker.ui.components.*
import com.galeria.medicationstracker.ui.theme.*

@Composable
fun DocDashboardScreen(
  modifier: Modifier = Modifier,
  viewModel: DocDashboardVM = viewModel()
) {
  val state = viewModel._docDashboardState.collectAsStateWithLifecycle()
  
  val testValues = state.value.tempList
  val patientsList = state.value.patientsList
  val headerValues = state.value.headerValuesUsers
  
  Column(modifier = modifier.fillMaxWidth()) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
      // Header Row.
      item {
        FlyRow(values = headerValues, isHeader = true)
      }
      
      items(patientsList!!.size) { index ->
        FlyRow(
          values = listOf<String>(patientsList[index].toString()),
          isHeader = false
        )
        
      }
      
      // Body Rows.
      /* items(testValues.size) { index ->
        FlyRow(values = testValues[index], isHeader = false)
      } */
      
    }
  }
}

@Composable
fun FlyRow(
  modifier: Modifier = Modifier,
  isHeader: Boolean = false,
  values: List<String>,
) {
  LazyRow(
    modifier = modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.Start,
  ) {
    items(values.size) { index ->
      FlyTableTextField(
        value = values[index],
        label = "",
        isHeader = isHeader,
        textStyles = MedTrackerTheme.typography.body,
        onValueChange = {},
        readOnly = true,
      )
    }
  }
}