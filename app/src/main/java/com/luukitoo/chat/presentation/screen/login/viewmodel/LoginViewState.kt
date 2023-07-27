package com.luukitoo.chat.presentation.screen.login.viewmodel

import com.luukitoo.chat.core.helper.DisposableValue
import com.luukitoo.chat.domain.model.User

data class LoginViewState(
    val email: String = "",
    val password: String = "",
    val failure: Throwable? = null,
    val success: DisposableValue<Boolean>? = null
)
