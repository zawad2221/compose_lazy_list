package com.example.interviewapplication.data.repo

import com.example.interviewapplication.data.model.UserList
import com.example.interviewapplication.data.network.ProgressBarState
import com.example.interviewapplication.data.network.Resource
import com.example.interviewapplication.data.remote.Api
import com.example.interviewapplication.utils.NetworkErrorHandling
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val api: Api,
    private val errorHandling: NetworkErrorHandling
) {
    fun getList(): Flow<Resource<UserList>> = flow {

        try {
            emit(Resource.Loading(progressBarState = ProgressBarState.Loading))
            delay(2000)
            val response = api.getData(page = 1)
            emit(Resource.Success(UserList(response)))
        } catch (e: Exception) {
            delay(2000)
            emit(errorHandling.handleError(e))
        } finally {
            delay(2000)
            emit(Resource.Loading(progressBarState = ProgressBarState.Idle))
        }

    }.flowOn(Dispatchers.IO).catch { e ->
        emit(errorHandling.handleError(e))
        emit(Resource.Loading(progressBarState = ProgressBarState.Idle))
    }
}