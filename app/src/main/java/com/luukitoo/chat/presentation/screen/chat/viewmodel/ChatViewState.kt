package com.luukitoo.chat.presentation.screen.chat.viewmodel

import com.luukitoo.chat.core.helper.DisposableValue
import com.luukitoo.chat.domain.model.Message
import com.luukitoo.chat.domain.model.User

data class ChatViewState(
    val userInfo: User = User(),
    val messages: List<Message> = emptyList(),
    val sendingText: String = "",
    val logOut: DisposableValue<Boolean>? = null,
    val newMessageAppeared: DisposableValue<Boolean>? = null
)
