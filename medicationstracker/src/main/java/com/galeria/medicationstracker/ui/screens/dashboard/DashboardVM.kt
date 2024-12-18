package com.galeria.medicationstracker.ui.screens.dashboard

import android.util.*
import androidx.lifecycle.*
import com.galeria.medicationstracker.data.*
import com.galeria.medicationstracker.model.FirestoreFunctions.*
import com.google.firebase.*
import com.google.firebase.auth.*
import com.google.firebase.firestore.*
import dagger.hilt.android.lifecycle.*
import kotlinx.coroutines.flow.*
import javax.inject.*

@HiltViewModel
class DashboardVM @Inject constructor() : ViewModel() {

  val currentUserId = FirebaseAuth.getInstance().currentUser?.uid
  private var _currentTakenMedications =
    MutableStateFlow<List<UserMedication>>(emptyList())
  var currentTakenMedications = _currentTakenMedications.asStateFlow()

  init {
    getCurrentMedications()
  }

  // Получение списка лекарств пациента.

  var showToastCallback: ((String) -> Unit)? = null

  // Фильтрация лекарств, прием которых окончен для использования при выводе на главный экран.
  fun getCurrentMedications() {

    FirestoreService.db.collection("UserMedication")
        .where(
          Filter.and(
            Filter.equalTo("uid", currentUserId),
            Filter.greaterThanOrEqualTo("endDate", Timestamp.now())
          )
        )
        .addSnapshotListener { medicationSnapshots, error ->
          if (error != null) {
            Log.e(
              "DashboardVM",
              "Error fetching current medications: ${error.message}",
              error
            )
            showToastCallback?.invoke("Error loading medications")
            return@addSnapshotListener
          }

          medicationSnapshots?.let {
            _currentTakenMedications.value =
              it.toObjects(UserMedication::class.java)
            showToastCallback?.invoke("Medications loaded successfully")
          }
        }
  }

}