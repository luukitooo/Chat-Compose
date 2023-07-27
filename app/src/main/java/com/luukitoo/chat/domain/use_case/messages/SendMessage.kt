package com.luukitoo.chat.domain.use_case.messages

import com.luukitoo.chat.core.base.UseCase
import com.luukitoo.chat.domain.model.Message
import com.luukitoo.chat.domain.repository.MessageRepository
import javax.inject.Inject

class SendMessage @Inject constructor(
    private val messageRepository: MessageRepository
) : UseCase<Message, Unit> {

    override suspend fun execute(parameter: Message) {
        messageRepository.sendMessage(parameter)
    }
}