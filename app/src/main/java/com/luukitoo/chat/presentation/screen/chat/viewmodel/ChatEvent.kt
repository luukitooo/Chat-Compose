package com.luukitoo.chat.presentation.screen.chat.viewmodel

sealed class ChatEvent {
    data class UpdateSendingText(val value: String) : ChatEvent()
    object ObserveMessages : ChatEvent()
    object GetUserInfo : ChatEvent()
    object SendMessage : ChatEvent()
    object LogOut : ChatEvent()
}