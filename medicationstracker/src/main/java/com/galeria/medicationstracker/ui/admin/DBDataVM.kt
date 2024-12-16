package com.galeria.medicationstracker.ui.admin

import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

data class DBDataState(
  // TODO.
  val temp: String = ""
)

class DBDataVM : ViewModel() {

  private val db = Firebase.firestore

  init {
    getData()
  }

  private fun getData() {
    // ...
  }

}