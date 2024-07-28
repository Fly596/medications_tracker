package com.example.speechrecognitionapp.ui.screens.meds_list

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.speechrecognitionapp.data.Medication
import com.google.firebase.Firebase
import com.google.firebase.appcheck.internal.util.Logger.TAG
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MedsScreenViewModel : ViewModel() {

    private var _medications = MutableStateFlow<List<Medication>>(emptyList())
    val medications = _medications.asStateFlow()

    private var db = Firebase.firestore

    init {
        getMedsList()
    }


    private fun getMedsList() {
        db.collection("medication")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    return@addSnapshotListener
                }

                if (value != null) {
                    _medications.value = value.toObjects()
                }

            }
    }


    fun addMedication() {
        val medication = hashMapOf(
            "name" to "Gabapentin",
            "type" to "Capsules"
        )

        db.collection("medication")
            .add(medication)
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot added with ID: ${it.id}")
            }
    }

    fun updateMedication(
        name: String = "",
        type: String = ""
    ) {
        val medication = hashMapOf(
            "name" to name,
            "type" to type
        )

        db.collection("medication")
            .document("addy")
            .set(medication)
            .addOnSuccessListener {
                Log.d(TAG, "Medication updated")
            }
    }


    private var _curMedication = MutableStateFlow<Medication?>(null)
    val curMedication = _curMedication.asStateFlow()

    fun getMedicationByName(name: String) {
        db.collection("medication")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    return@addSnapshotListener

                }
                if (value != null) {
                    _curMedication.value = medications.value[getId(name)]
                }
            }
    }

    private fun getId(name: String): Int {
        var id: Int = 0

        val itr = _medications.asStateFlow().value.listIterator()

        while (itr.hasNext()) {
            if (itr.next().name == name) {
                id = itr.previousIndex()
                break
            }
        }


        return id
    }
}
