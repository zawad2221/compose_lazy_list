package com.example.interviewapplication.view.navigation

import androidx.annotation.StringRes
import com.example.interviewapplication.R

enum class Screens {
    HOME {
        override fun getTitle() = R.string.home
    },
    DETAILS {
        override fun getTitle() = R.string.details
    };

    @StringRes
    abstract fun getTitle(): Int
}