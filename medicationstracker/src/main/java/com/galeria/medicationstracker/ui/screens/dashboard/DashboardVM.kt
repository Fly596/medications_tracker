package com.galeria.medicationstracker.ui.screens.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class DashboardScreenUiState(
  val testData: String = ""
)

class DashboardVM : ViewModel() {

  var uiState by mutableStateOf(DashboardScreenUiState())
    private set


}