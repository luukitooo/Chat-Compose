package com.luukitoo.chat.di.module

import com.luukitoo.chat.data.repository.FirebaseMessageRepository
import com.luukitoo.chat.data.repository.FirebaseUserRepository
import com.luukitoo.chat.domain.repository.MessageRepository
import com.luukitoo.chat.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindFirebaseUserRepository(
        firebaseUserRepository: FirebaseUserRepository
    ): UserRepository

    @Binds
    @Singleton
    fun bindFirebaseMessageRepository(
        firebaseMessageRepository: FirebaseMessageRepository
    ): MessageRepository
}