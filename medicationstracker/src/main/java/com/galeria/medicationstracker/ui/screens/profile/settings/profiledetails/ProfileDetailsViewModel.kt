package com.galeria.medicationstracker.ui.screens.profile.settings.profiledetails

import androidx.lifecycle.ViewModel
import com.galeria.medicationstracker.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileDetailsViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
}
