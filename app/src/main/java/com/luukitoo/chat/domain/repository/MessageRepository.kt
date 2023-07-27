package com.luukitoo.chat.domain.repository

import com.luukitoo.chat.domain.model.Message

interface MessageRepository {

    suspend fun sendMessage(message: Message)

    fun observeMessages(observer: (List<Message>) -> Unit)
}