package com.example.projectblueprint.framework.base

sealed class IOTaskResult<out O : Any> {
    data class OnSuccess<out O : Any>(val data: O) : IOTaskResult<O>()
    data class OnFailed(val throwable: Throwable) : IOTaskResult<Nothing>()
}