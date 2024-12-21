package com.galeria.medicationstracker.ui.screens.dashboard.record

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

@Composable
fun RecordsScreen(
    modifier: Modifier = Modifier,
    viewModel: RecordsVM = viewModel(),
    onConfirmRecord: () -> Unit = {}
) {

  val intakes by viewModel.intakesData.collectAsStateWithLifecycle()

  Column(
    modifier = modifier.fillMaxSize(),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {

    // Displays the screen title.
    Text(
      "Logs",
      style = MedTrackerTheme.typography.largeTitleEmphasized,
    )
    Spacer(modifier = Modifier.padding(8.dp))

    LazyColumn() {
      items(intakes) { intake ->
        Text(text = "${intake.medicationName}")
        Text(text = "${intake.status}")
      }
    }
  }

}

