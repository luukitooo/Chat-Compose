package com.luukitoo.chat.data.model

import com.luukitoo.chat.domain.model.Message

data class MessageDto(
    val id: String? = null,
    val text: String? = null,
    val senderId: String? = null,
    val senderUsername: String? = null,
    val time: Long? = null
) {
    fun toMessage() = Message(
        id = id,
        text = text,
        senderId = senderId,
        senderUsername = senderUsername,
        time = time
    )
}
