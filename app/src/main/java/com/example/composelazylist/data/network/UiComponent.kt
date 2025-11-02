package com.example.composelazylist.data.network

sealed class UiComponent(val msg: String) {
    data class Toast(val message: String) : UiComponent(msg = message)
    data class Custom(val message: String) : UiComponent(msg = message)
}
