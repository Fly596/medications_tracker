package com.galeria.medicationstracker.ui.screens.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galeria.medicationstracker.data.User
import com.galeria.medicationstracker.data.UserIntake
import com.galeria.medicationstracker.data.UserMedication
import com.galeria.medicationstracker.data.UserRepository
import com.galeria.medicationstracker.utils.FirestoreFunctions.FirestoreService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.SetOptions.merge
import com.google.firebase.firestore.Source
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProfileScreenUiState(
    // val testData: String = "",
    val user: User? = null,
    // val doctors: List<User> = listOf(),
    // val selectedDoctor: User? = null,
    // val selectedTime: String = "",
    // val selectedDate: Timestamp = Timestamp.now(),
    val age: Int = 0,
    val weight: Float = 0.0f,
    val height: Float = 0.0f,
    val name: String = "",
    val intakes: List<UserIntake> = emptyList(),
    val medications: List<UserMedication> = emptyList(),
)

@HiltViewModel
class ProfileVM @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(ProfileScreenUiState())
    val uiState = _uiState.asStateFlow()
    val db = FirestoreService.db
    private val firebaseAuth = FirebaseAuth.getInstance()
    val currentUser = firebaseAuth.currentUser
    private val currentUserId = firebaseAuth.currentUser?.uid
    
    init {
        viewModelScope.launch {
            val user = repository.getUserData(currentUserId.toString())
            val intakes = repository.getUserIntakes(currentUserId.toString())
            val medications = repository.getUserDrugs(currentUserId.toString())
            _uiState.value =
                _uiState.value.copy(user = user, intakes = intakes, medications = medications)
        }
    }
    
    private fun fetchUserData() {
        viewModelScope.launch {
            val userRef = db.collection("User")
                .document(currentUser?.email.toString())
            val source = Source.DEFAULT
            
            try {
                userRef.get(source)
                    .addOnSuccessListener { result ->
                        val user = result.toObject(User::class.java)
                        _uiState.value = _uiState.value.copy(user = user)
                    }
                    .addOnFailureListener { exp ->
                        println("Error fetching user data: ${exp.message}")
                    }
            } catch (e: Exception) {
                println("Error fetching user data: ${e.message}")
            }
        }
        
    }
    
    /*
        private fun fetchDoctors() {
            viewModelScope.launch {
                val userRef = db.collection("User")
    
                try {
                    userRef.whereEqualTo(
                        "type",
                        "DOCTOR"
                    )
                        .get()
                        .addOnSuccessListener { docs ->
                            val doctors = docs.toObjects(User::class.java)
    
                            _uiState.value = _uiState.value.copy(doctors = doctors)
                        }
                        .addOnFailureListener { exp ->
                            Log.d(
                                "ProfileVM",
                                "Error fetching docs data: ${exp.message}"
                            )
                        }
                } catch (e: Exception) {
                    println("Error fetching docs data: ${e.message}")
    
                }
            }
        }
    */
    /*     fun addAppointment() {
            viewModelScope.launch {
                val docRef = db.collection("Appointments")
                val appointment = Appointment(
                    date = _uiState.value.selectedDate,
                    time = _uiState.value.selectedTime,
                    doctor = _uiState.value.selectedDoctor?.uid,
                    patient = currentUserId,
                )
    
                docRef.document("${currentUser?.email}${_uiState.value.selectedDoctor?.name}${_uiState.value.selectedDate}")
                    .set(appointment)
                    .addOnSuccessListener {
                        Log.d("ProfileVM", "DocumentSnapshot added successfully!")
                    }
                    .addOnFailureListener { e ->
                        Log.w("ProfileVM", "Error adding document", e)
                    }
            }
    
        } */
    fun updateAgeFirestore() {
        val userRef = db.collection("User")
            .document(currentUser?.email.toString())
        
        userRef.set(
            mapOf("age" to _uiState.value.age),
            merge()
        )
            .addOnSuccessListener {
                Log.d("ProfileVM", "DocumentSnapshot added successfully!")
            }
            .addOnFailureListener { e ->
                Log.w("ProfileVM", "Error adding document", e)
            }
    }
    
    fun updateWeightFirestore() {
        val userRef = db.collection("User")
            .document(currentUser?.email.toString())
        
        userRef.set(
            mapOf("weight" to _uiState.value.weight),
            merge()
        )
            .addOnSuccessListener {
                Log.d("ProfileVM", "DocumentSnapshot added successfully!")
            }
            .addOnFailureListener { e ->
                Log.w("ProfileVM", "Error adding document", e)
            }
    }
    
    fun updateHeightFirestore() {
        val userRef = db.collection("User")
            .document(currentUser?.email.toString())
        
        userRef.set(
            mapOf("height" to _uiState.value.height),
            merge()
        )
            .addOnSuccessListener {
                Log.d("ProfileVM", "DocumentSnapshot added successfully!")
            }
            .addOnFailureListener { e ->
                Log.w("ProfileVM", "Error adding document", e)
            }
    }
    
    fun updateNameFirestore() {
        val userRef = db.collection("User")
            .document(currentUser?.email.toString())
        userRef.set(
            mapOf("name" to _uiState.value.name),
            merge()
        )
            .addOnSuccessListener {
                Log.d("ProfileVM", "DocumentSnapshot added successfully!")
            }
            .addOnFailureListener { e ->
                Log.w("ProfileVM", "Error adding document", e)
            }
    }
    
    fun updateName(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
    }
    
    fun updateAge(age: Int) {
        _uiState.value = _uiState.value.copy(age = age)
    }
    
    fun updateWeight(weight: Float) {
        _uiState.value = _uiState.value.copy(weight = weight)
    }
    
    fun updateHeight(height: Float) {
        _uiState.value = _uiState.value.copy(height = height)
    }
    /*     fun updateSelectedDoctor(doctor: User? = null) {
            _uiState.value = _uiState.value.copy(selectedDoctor = doctor)
        }
    
        fun updateSelectedTime(time: String) {
            _uiState.value = _uiState.value.copy(selectedTime = time)
        }
    
        fun updateSelectedDate(date: Timestamp?) {
            _uiState.value = _uiState.value.copy(selectedDate = date ?: Timestamp.now())
        } */
}
