package com.galeria.medicationstracker.ui.doctor

import androidx.lifecycle.*
import com.galeria.medicationstracker.data.*
import com.google.firebase.*
import com.google.firebase.firestore.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*

data class DocDashboardState(
  val patientsList: List<User>? = null,
  val headerValuesUsers: List<String> = USER_HEADERS,
  val tempList: List<List<String>> = listOf(
    listOf<String>(
      "test1",
      "test1",
      "test1",
    ),
    listOf<String>(
      "test2",
      "test2",
      "test2",
    ),
    listOf<String>(
      "test3",
      "test3",
      "test3",
    ),
    listOf<String>(
      "test4",
      "test4",
      "test4",
    )
  )
) {
  
  companion object {
    
    val USER_HEADERS = listOf(
      "UID",
      "Login",
      "type"
    )
  }
}

class DocDashboardVM : ViewModel() {
  
  private val db = Firebase.firestore
  
  private var docDashboardState = MutableStateFlow(DocDashboardState())
  val _docDashboardState = docDashboardState.asStateFlow()
  
  private var _users = MutableStateFlow<List<User>>(emptyList())
  var users = _users.asStateFlow()
  
  
  init {
    /*     viewModelScope.launch {
          getPatientsList().collect { patients ->
            docDashboardState.value =
              docDashboardState.value.copy(patientsList = patients)
          }
        } */
  }
  
  private fun getPatientsList():
      Flow<List<User>> = callbackFlow {
    val listener = db.collection("users")
      .addSnapshotListener { value, error ->
        if (error != null) {
          close(error)
          return@addSnapshotListener
        }
        if (value != null) {
          _users.value = value.toObjects()
          // trySend(value.toObjects(User::class.java))
        }
      }
    awaitClose { listener.remove() }
  }
  
  /*     db.collection("users")
        .addSnapshotListener { value, error ->
          if (error != null) {
            Log.e("PatientListViewModel", "Error fetching patients", error)
            
            return@addSnapshotListener
          }
          
          if (value != null) {
            docDashboardState.value =
              docDashboardState.value.copy(patientsList = value.toObjects(User::class.java))
          }
          
        } */
  
  fun updatePatientsList(newList: List<User>) {
    docDashboardState.value =
      docDashboardState.value.copy(patientsList = newList)
  }
}