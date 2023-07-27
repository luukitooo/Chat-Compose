package com.luukitoo.chat.domain.model

import com.luukitoo.chat.data.model.MessageDto

data class Message(
    val id: String?,
    val text: String?,
    val senderId: String?,
    val senderUsername: String?,
    val time: Long?
) {
    fun toMessageDto() = MessageDto(
        id = id,
        text = text,
        senderId = senderId,
        senderUsername = senderUsername,
        time = time
    )
}
