package com.example.composelazylist.view

import com.example.composelazylist.data.model.UserItem

sealed interface MainAction {
    data class OpenDetails(val userItem: UserItem) : MainAction
    data object OnBack : MainAction
}