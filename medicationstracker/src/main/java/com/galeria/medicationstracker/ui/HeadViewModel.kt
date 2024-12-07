package com.galeria.medicationstracker.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

// TODO: What is this?
class HeadViewModel : ViewModel() {

  private val _selectedItemIndex = MutableStateFlow(0)
  val selectedItemIndex = _selectedItemIndex.asStateFlow()

  fun updateSelectedItemIndex(index: Int) {
    _selectedItemIndex.value = index
  }
}
