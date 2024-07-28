package com.example.speechrecognitionapp.model.service

import com.example.speechrecognitionapp.data.AppUser
import kotlinx.coroutines.flow.Flow

interface AccountService {
    val currentUser: Flow<AppUser?>
    val currentUserId: String
    fun hasUser(): Boolean
    suspend fun signIn(email: String, password: String)
    suspend fun signUp(email: String, password: String)
    suspend fun signOut()
    suspend fun deleteAccount()
}