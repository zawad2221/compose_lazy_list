package com.example.composelazylist.view.navigation

import androidx.annotation.StringRes
import com.example.composelazylist.R

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