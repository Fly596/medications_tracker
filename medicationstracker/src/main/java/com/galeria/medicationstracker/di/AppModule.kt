package com.galeria.medicationstracker.di

import com.galeria.medicationstracker.data.MedicationsRepository
import com.galeria.medicationstracker.data.MedicationsRepositoryImpl
import com.galeria.medicationstracker.data.UserRepository
import com.galeria.medicationstracker.data.UserRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()
    
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
    
    @Provides
    @Singleton
    fun provideFirestoreRepository(
        firestore: FirebaseFirestore
    ): MedicationsRepository {
        return MedicationsRepositoryImpl(firestore)
    }
    
    @Provides
    @Singleton
    fun provideUserRepository(
        firestore: FirebaseFirestore,
        auth: FirebaseAuth
    ): UserRepository {
        return UserRepositoryImpl(firestore, auth)
    }
}