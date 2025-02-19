package com.galeria.medicationstracker.ui.screens.profile.notes

import androidx.lifecycle.ViewModel
import com.galeria.medicationstracker.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class NotesScreenUiState(
    val testData: String = "",
)

@HiltViewModel
class NotesScreenViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(NotesScreenUiState())
    val uiState = _uiState.asStateFlow()
}