package com.example.projectblueprint.framework.base

sealed class ViewState<out T : Any> {
    data class Loading(val isLoading: Boolean) : ViewState<Nothing>()
    data class RenderSuccess<out T : Any>(val output: T) : ViewState<T>()
    data class RenderFailure(val throwable: Throwable) : ViewState<Nothing>()
    data class Empty(val isEmpty: Boolean = true): ViewState<Nothing>()
}