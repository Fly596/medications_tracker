package com.galeria.medicationstracker.model

import com.google.firebase.firestore.*

public class FirestoreFunctions() {
  object FirestoreService {

    val db: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }
  }

  
}
