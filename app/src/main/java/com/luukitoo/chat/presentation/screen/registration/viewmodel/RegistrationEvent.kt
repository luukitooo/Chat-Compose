package com.luukitoo.chat.presentation.screen.registration.viewmodel

sealed class RegistrationEvent {
    data class UpdateUsername(val value: String) : RegistrationEvent()
    data class UpdateEmail(val value: String) : RegistrationEvent()
    data class UpdatePassword(val value: String) : RegistrationEvent()
    object TryToRegister : RegistrationEvent()
}