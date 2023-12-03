package com.logical.techtask.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

/**
 * A base ViewModel class that provides common functionality for managing view state in the app.
 *
 * The `BaseViewModel` class is an abstract ViewModel that implements the `ViewModel` class provided
 * by Android Jetpack. It is designed to be used as a base class for other ViewModel classes that need
 * to manage view state in the app.
 */
interface ViewState
abstract class BaseViewModel<VIEW_STATE : ViewState> : ViewModel() {
    abstract fun initialState(): VIEW_STATE

    private val _viewState = mutableStateOf(initialState())
    val viewState: State<VIEW_STATE> = _viewState

    fun updateState(updatedState: (lastState: VIEW_STATE) -> VIEW_STATE) {
        _viewState.value = updatedState(currentViewState())
    }

    fun currentViewState() = _viewState.value
}