package com.galeria.medicationstracker.ui.doctor.patients

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons.AutoMirrored.Filled
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PatientsListScreen(
  modifier: Modifier = Modifier,
  viewModel: PatientsListViewModel = viewModel(),
  onGoBack: () -> Unit = {}
) {
  val uiState = viewModel.uiState.collectAsStateWithLifecycle()

  val patientsList = uiState.value.patients

  Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp)) {

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
    ) {
      IconButton(
        onClick = { onGoBack.invoke() }
      ) {
        Icon(imageVector = Filled.ArrowBack, contentDescription = "back")
      }

      Text(text = "Patients List Screen", style = MaterialTheme.typography.titleMedium)
    }


  }

}
