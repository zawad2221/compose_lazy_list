package com.example.interviewapplication.data.repo

import com.example.interviewapplication.data.model.UserList
import com.example.interviewapplication.data.network.Resource
import com.example.interviewapplication.data.remote.Api
import com.example.interviewapplication.utils.NetworkErrorHandling
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val api: Api,
    private val errorHandling: NetworkErrorHandling
) {
    suspend fun getList(page: Int = 1, size: Int = 10): Resource<UserList> {
        try {
            val response = api.getData(page = page, limit = size)
            return (Resource.Success(UserList(response)))
        } catch (e: Exception) {
            return (errorHandling.handleError(e))
        }
    }
}