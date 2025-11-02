package com.example.interviewapplication.view

import com.example.interviewapplication.data.model.UserItem

sealed interface MainAction {
    data class OpenDetails(val userItem: UserItem) : MainAction
    data object OnBack : MainAction
}