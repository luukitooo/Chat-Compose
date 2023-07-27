package com.luukitoo.chat.presentation.screen.registration.viewmodel

import com.luukitoo.chat.core.helper.DisposableValue
import com.luukitoo.chat.domain.model.User

data class RegistrationViewState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val failure: Throwable? = null,
    val success: DisposableValue<User>? = null
)
