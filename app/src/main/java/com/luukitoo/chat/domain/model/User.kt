package com.luukitoo.chat.domain.model

import com.luukitoo.chat.data.model.UserDto

data class User(
    val id: String? = null,
    val username: String? = null
) {
    fun toUserDto() = UserDto(
        id = id,
        username = username
    )
}
