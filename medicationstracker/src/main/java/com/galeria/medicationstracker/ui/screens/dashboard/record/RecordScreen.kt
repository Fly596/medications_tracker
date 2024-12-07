package com.galeria.medicationstracker.ui.screens.dashboard.record

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

@Composable
fun RecordScreen(
  modifier: Modifier = Modifier,
  viewModel: RecordVM = viewModel(),
  onConfirmRecord: () -> Unit = {}
) {

  Column(
    modifier = modifier.fillMaxSize(),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {

    // Displays the screen title.
    Text(
      "Edit medication",
      style = MedTrackerTheme.typography.largeTitleEmphasized,
    )
    Spacer(modifier = Modifier.padding(8.dp))
  }


}