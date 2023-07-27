package com.luukitoo.chat.presentation.screen.login.viewmodel

sealed class LoginEvent {
    data class UpdateEmail(val value: String) : LoginEvent()
    data class UpdatePassword(val value: String) : LoginEvent()
    object TryToLogin : LoginEvent()
}