package com.example.interviewapplication.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class UserItem(
    @Json(name = "body")
    val body: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "userId")
    val userId: Int?
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class UserList(
    val userList: List<UserItem> = listOf()
) : Parcelable