package com.luukitoo.chat.presentation.screen.chat.viewmodel

import com.google.firebase.auth.FirebaseAuth
import com.luukitoo.chat.core.base.StatefulViewModel
import com.luukitoo.chat.core.extension.launch
import com.luukitoo.chat.core.helper.DisposableValue
import com.luukitoo.chat.domain.model.Message
import com.luukitoo.chat.domain.use_case.messages.ObserveMessages
import com.luukitoo.chat.domain.use_case.messages.SendMessage
import com.luukitoo.chat.domain.use_case.user.GetUserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getUserInfo: GetUserInfo,
    private val observeMessages: ObserveMessages,
    private val sendMessage: SendMessage,
    private val auth: FirebaseAuth
) : StatefulViewModel<ChatViewState, ChatEvent>(
    initialState = ChatViewState()
) {

    init {
        onEvent(ChatEvent.GetUserInfo)
        onEvent(ChatEvent.ObserveMessages)
    }

    override fun onEvent(event: ChatEvent) {
        when (event) {
            is ChatEvent.UpdateSendingText -> updateSendingText(event.value)
            is ChatEvent.SendMessage -> sendMessage()
            is ChatEvent.GetUserInfo -> getUserInfo()
            is ChatEvent.ObserveMessages -> observeMessages()
            is ChatEvent.LogOut -> logOut()
        }
    }

    private fun logOut() {
        auth.signOut()
        updateState { copy(
            logOut = DisposableValue(true)
        ) }
    }

    private fun getUserInfo() = launch {
        getUserInfo.execute(id = auth.currentUser?.uid ?: "").also {
            updateState { copy(
                userInfo = it
            ) }
        }
    }

    private fun observeMessages() = launch {
        observeMessages.execute { messages ->
            updateState { copy(
                messages = messages.sortedBy { it.time },
                newMessageAppeared = DisposableValue(true)
            ) }
        }
    }

    private fun sendMessage() = launch {
        if (viewState.sendingText.isNotBlank()) {
            sendMessage.execute(
                parameter = Message(
                    id = UUID.randomUUID().toString(),
                    text = viewState.sendingText,
                    senderId = viewState.userInfo.id,
                    senderUsername = viewState.userInfo.username,
                    time = System.currentTimeMillis()
                )
            )
            updateState { copy(sendingText = "") }
        }
    }

    private fun updateSendingText(newValue: String) = updateState {
        copy(sendingText = newValue)
    }
}