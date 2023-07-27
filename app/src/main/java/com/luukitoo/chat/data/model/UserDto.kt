package com.luukitoo.chat.data.model

import com.luukitoo.chat.domain.model.User

data class UserDto(
    val id: String? = null,
    val username: String? = null
) {
    fun toUser() = User(
        id = id,
        username = username
    )
}
