package com.example.speechrecognitionapp.ui.screens.medication

/*
class MedScreenViewModel : ViewModel() {
    private var _medication = MutableStateFlow<Medication?>(null)
    val medication = _medication.asStateFlow()

    private var db = Firebase.firestore


    init {
        getMedicationByName()
    }

    private fun getMedicationByName() {

        db.collection("medication")
            .document("addy")
            .get()
            .addOnSuccessListener { document ->
                _medication.value = document.toObject()

            }
    }

    fun getMedByName(name: String) {
        db.collection("medication")
            .document(name)
    }



}*/
