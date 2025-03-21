package com.justadev.underwaterworldtesttask.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State, Intent>(initialState: State) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<State> get() = _uiState

    protected var currentState: State
        get() = _uiState.value
        set(value) {
            _uiState.value = value
        }

    abstract fun onEvent(intent: Intent)

    protected fun launchSafely(
        block: suspend CoroutineScope.() -> Unit,
        onError: (Throwable) -> Unit = { handleException(it) }
    ) {
        viewModelScope.launch {
            try {
                block()
            } catch (e: Throwable) {
                onError(e)
            }
        }
    }

    open fun handleException(exception: Throwable) {
        exception.printStackTrace()
    }

    protected fun updateState(reducer: State.() -> State) {
        currentState = currentState.reducer()
    }
}