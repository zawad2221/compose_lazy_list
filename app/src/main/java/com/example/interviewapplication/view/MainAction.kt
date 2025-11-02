package com.example.interviewapplication.view

sealed interface MainAction {
    data object OpenDetails : MainAction
    data object OnBack : MainAction
}