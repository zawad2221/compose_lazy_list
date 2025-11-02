package com.example.interviewapplication.utils

fun String?.getScreenNameFromRoute() = this?.substringBefore(
    "?"
)?.substringBefore("/").hashCode()