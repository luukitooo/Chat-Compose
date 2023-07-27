package com.luukitoo.chat.domain.use_case.messages

import com.luukitoo.chat.core.base.UseCase
import com.luukitoo.chat.domain.model.Message
import com.luukitoo.chat.domain.repository.MessageRepository
import javax.inject.Inject

class ObserveMessages @Inject constructor(
    private val messageRepository: MessageRepository
) : UseCase<(List<Message>) -> Unit, Unit> {

    override suspend fun execute(parameter: (List<Message>) -> Unit) {
        messageRepository.observeMessages { messages ->
            parameter.invoke(messages)
        }
    }
}