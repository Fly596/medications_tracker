package com.galeria.medicationstracker.ui.shared.components

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HeadViewModel : ViewModel() {


    private val _selectedItemIndex = MutableStateFlow(0)
    val selectedItemIndex = _selectedItemIndex.asStateFlow()

    fun updateSelectedItemIndex(index: Int) {
        _selectedItemIndex.value = index
    }

}