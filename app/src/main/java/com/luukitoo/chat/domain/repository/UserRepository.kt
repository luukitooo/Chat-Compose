package com.luukitoo.chat.domain.repository

import com.luukitoo.chat.domain.model.User

interface UserRepository {

    suspend fun saveUser(user: User)

    suspend fun getUser(id: String): User
}