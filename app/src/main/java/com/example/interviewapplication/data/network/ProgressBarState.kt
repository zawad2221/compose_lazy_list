package com.example.interviewapplication.data.network

sealed class ProgressBarState {
    object Loading : ProgressBarState()
    object Idle : ProgressBarState()
}