package com.galeria.medicationstracker.di

import com.galeria.medicationstracker.data.FirebaseRepository
import com.galeria.medicationstracker.data.FirebaseRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()
    
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
    
    @Provides
    fun provideFirestoreRepository(
        firestore: FirebaseFirestore
    ): FirebaseRepository {
        return FirebaseRepositoryImpl(firestore)
    }
    
}