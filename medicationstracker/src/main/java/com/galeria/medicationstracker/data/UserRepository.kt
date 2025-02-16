package com.galeria.medicationstracker.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface UserRepository {
    
    suspend fun addUser()
    suspend fun deleteUser()
    suspend fun getUserData(uid: String): User
    suspend fun updateUserData(user: User)
    
}

class UserRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
) : UserRepository {
    
    override suspend fun addUser() {
        TODO("Not yet implemented")
    }
    
    override suspend fun deleteUser() {
        TODO("Not yet implemented")
    }
    
    override suspend fun getUserData(uid: String): User {
        return try {
            val userRef = firestore.collection("User")
                .document(auth.currentUser?.email.toString())
                .get()
                .await()
            userRef.toObject(User::class.java)!!
        } catch (e: Exception) {
            User()
        }
        
    }
    
    override suspend fun updateUserData(user: User) {
        TODO("Not yet implemented")
    }
    
}