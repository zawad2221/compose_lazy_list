package com.example.composelazylist.utils

import com.example.composelazylist.BuildConfig

object Logger {
    fun isDebug(): Boolean = BuildConfig.DEBUG
}