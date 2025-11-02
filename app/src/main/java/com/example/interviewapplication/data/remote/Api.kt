package com.example.interviewapplication.data.remote

import com.example.interviewapplication.data.model.UserItem
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/posts")
    suspend fun getData(
        @Query("_page")
        page: Int,
        @Query("_limit")
        limit: Int = 10
    ): List<UserItem>
}