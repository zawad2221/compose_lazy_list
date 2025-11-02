package com.example.interviewapplication.utils

import com.example.interviewapplication.BuildConfig

object Logger {
    fun isDebug(): Boolean = BuildConfig.DEBUG
}