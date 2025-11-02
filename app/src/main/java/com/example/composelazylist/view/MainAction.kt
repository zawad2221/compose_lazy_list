package com.example.composelazylist.view

sealed interface MainAction {
    data object OpenDetails : MainAction
    data object OnBack : MainAction
}