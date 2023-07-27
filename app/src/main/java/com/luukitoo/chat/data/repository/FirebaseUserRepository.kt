package com.luukitoo.chat.data.repository

import com.google.firebase.firestore.CollectionReference
import com.luukitoo.chat.data.model.UserDto
import com.luukitoo.chat.di.qualifier.UsersCollection
import com.luukitoo.chat.domain.model.User
import com.luukitoo.chat.domain.repository.UserRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseUserRepository @Inject constructor(
    @UsersCollection private val usersReference: CollectionReference
) : UserRepository {

    override suspend fun saveUser(user: User) {
        usersReference.add(user.toUserDto()).await()
    }

    override suspend fun getUser(id: String): User {
        return usersReference.whereEqualTo("id", id)
            .get()
            .await()
            .documents[0]
            .toObject(UserDto::class.java)
            ?.toUser() ?: User()
    }
}