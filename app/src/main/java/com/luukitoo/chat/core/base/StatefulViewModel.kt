package com.luukitoo.chat.core.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

abstract class StatefulViewModel<ViewState, Event>(
    private val initialState: ViewState
) : ViewModel() {

    var viewState by mutableStateOf(initialState)
        private set

    abstract fun onEvent(event: Event)

    protected fun updateState(stateBuilder: ViewState.() -> ViewState) {
        viewState = stateBuilder.invoke(viewState)
    }

    fun refreshState() {
        viewState = initialState
    }
}