package com.luukitoo.chat.presentation.screen.login.viewmodel

import com.luukitoo.chat.core.base.StatefulViewModel
import com.luukitoo.chat.core.extension.launch
import com.luukitoo.chat.core.helper.DisposableValue
import com.luukitoo.chat.domain.model.User
import com.luukitoo.chat.domain.use_case.user.LoginModel
import com.luukitoo.chat.domain.use_case.user.LoginUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUser: LoginUser
) : StatefulViewModel<LoginViewState, LoginEvent>(
    initialState = LoginViewState()
) {

    override fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.UpdateEmail -> updateEmail(event.value)
            is LoginEvent.UpdatePassword -> updatePassword(event.value)
            is LoginEvent.TryToLogin -> tryToLogin()
        }
    }

    private fun tryToLogin() = launch {
        updateState { copy(failure = null) }
        loginUser.execute(
            parameter = LoginModel(
                email = viewState.email,
                password = viewState.password
            )
        ).addOnSuccessListener {
            updateState { copy(
                success = DisposableValue(true)
            ) }
        }.addOnFailureListener {
            updateState { copy(
                failure = it
            ) }
        }
    }

    private fun updatePassword(newValue: String) = updateState {
        copy(password = newValue)
    }

    private fun updateEmail(newValue: String) = updateState {
        copy(email = newValue)
    }
}