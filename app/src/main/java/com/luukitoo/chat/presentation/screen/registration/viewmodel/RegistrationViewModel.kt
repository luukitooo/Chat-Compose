package com.luukitoo.chat.presentation.screen.registration.viewmodel

import com.luukitoo.chat.core.base.StatefulViewModel
import com.luukitoo.chat.core.extension.launch
import com.luukitoo.chat.core.helper.DisposableValue
import com.luukitoo.chat.domain.model.User
import com.luukitoo.chat.domain.use_case.user.RegisterUser
import com.luukitoo.chat.domain.use_case.user.RegistrationModel
import com.luukitoo.chat.domain.use_case.validator.ValidateFieldValues
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registerUser: RegisterUser,
    private val validateFieldValues: ValidateFieldValues
) : StatefulViewModel<RegistrationViewState, RegistrationEvent>(
    initialState = RegistrationViewState()
) {

    override fun onEvent(event: RegistrationEvent) {
        when (event) {
            is RegistrationEvent.UpdateUsername -> updateUsername(event.value)
            is RegistrationEvent.UpdateEmail -> updateEmail(event.value)
            is RegistrationEvent.UpdatePassword -> updatePassword(event.value)
            is RegistrationEvent.TryToRegister -> tryToRegister()
        }
    }

    private fun tryToRegister() = launch {
        val fieldsNotEmpty = validateFieldValues.execute(listOf(
            viewState.username,
            viewState.email,
            viewState.password
        ))
        if (fieldsNotEmpty) {
            updateState { copy(failure = null) }
            registerUser.execute(
                parameter = RegistrationModel(
                    username = viewState.username,
                    email = viewState.email,
                    password = viewState.password
                )
            ).addOnSuccessListener { authResult ->
                updateState {
                    copy(
                        success = DisposableValue(
                            User(
                                id = authResult.user?.uid,
                                username = viewState.username
                            )
                        )
                    )
                }
            }.addOnFailureListener {
                updateState { copy(failure = it) }
            }
        }
    }

    private fun updatePassword(newValue: String) = updateState {
        copy(password = newValue)
    }

    private fun updateEmail(newValue: String) = updateState {
        copy(email = newValue)
    }

    private fun updateUsername(newValue: String) = updateState {
        copy(username = newValue)
    }
}