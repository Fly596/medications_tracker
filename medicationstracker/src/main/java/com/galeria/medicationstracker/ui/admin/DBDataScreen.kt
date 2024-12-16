package com.galeria.medicationstracker.ui.admin

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun DBDataScreen(
  modifier: Modifier = Modifier,
  onProfileClick: () -> Unit = {},
  viewModel: DBDataVM = viewModel()
) {
  Column {
    Text("Admin Screen")
  }
}