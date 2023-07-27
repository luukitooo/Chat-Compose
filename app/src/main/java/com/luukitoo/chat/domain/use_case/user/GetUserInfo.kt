package com.luukitoo.chat.domain.use_case.user

import com.luukitoo.chat.core.base.UseCase
import com.luukitoo.chat.domain.model.User
import com.luukitoo.chat.domain.repository.UserRepository
import javax.inject.Inject

class GetUserInfo @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<String, User> {

    override suspend fun execute(id: String): User {
        return userRepository.getUser(id)
    }
}