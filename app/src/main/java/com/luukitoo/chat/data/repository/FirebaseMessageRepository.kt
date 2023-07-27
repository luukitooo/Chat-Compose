package com.luukitoo.chat.data.repository

import com.google.firebase.firestore.CollectionReference
import com.luukitoo.chat.core.extension.notNull
import com.luukitoo.chat.data.model.MessageDto
import com.luukitoo.chat.di.qualifier.MessagesCollection
import com.luukitoo.chat.domain.model.Message
import com.luukitoo.chat.domain.repository.MessageRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseMessageRepository @Inject constructor(
    @MessagesCollection private val messagesReference: CollectionReference
) : MessageRepository {

    override suspend fun sendMessage(message: Message) {
        messagesReference.add(message.toMessageDto()).await()
    }

    override fun observeMessages(observer: (List<Message>) -> Unit) {
        messagesReference.addSnapshotListener { value, error ->
            if (error.notNull()) return@addSnapshotListener
            observer.invoke(
                value?.toObjects(MessageDto::class.java)?.map(MessageDto::toMessage) ?: emptyList()
            )
        }
    }
}