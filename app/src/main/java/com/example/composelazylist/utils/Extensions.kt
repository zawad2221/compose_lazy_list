package com.example.composelazylist.utils

fun String?.getScreenNameFromRoute() = this?.substringBefore(
    "?"
)?.substringBefore("/").hashCode()