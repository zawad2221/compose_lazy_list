package com.example.interviewapplication.data.network

sealed class Resource<T>(val data: T? = null) {
    class Success<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(val uiComponent: UiComponent, val type: ErrorType) : Resource<T>()
    class Loading<T>(
        val progressBarState: ProgressBarState = ProgressBarState.Idle,
        data: T? = null
    ) : Resource<T>(data)
}