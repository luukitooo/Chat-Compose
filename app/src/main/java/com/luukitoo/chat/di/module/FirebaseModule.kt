package com.luukitoo.chat.di.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.luukitoo.chat.di.qualifier.MessagesCollection
import com.luukitoo.chat.di.qualifier.UsersCollection
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    private const val MESSAGES_COLLECTION = "MESSAGES_COLLECTION"
    private const val USERS_COLLECTION = "USERS_COLLECTION"

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    @MessagesCollection
    fun provideMessagesCollection(): CollectionReference {
        return Firebase.firestore.collection(MESSAGES_COLLECTION)
    }

    @Provides
    @Singleton
    @UsersCollection
    fun provideUsersCollection(): CollectionReference {
        return Firebase.firestore.collection(USERS_COLLECTION)
    }
}