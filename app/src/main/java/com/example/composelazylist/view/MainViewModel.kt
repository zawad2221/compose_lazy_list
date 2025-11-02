package com.example.composelazylist.view

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.composelazylist.data.model.UserList
import com.example.composelazylist.data.network.Resource
import com.example.composelazylist.data.repo.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import com.example.composelazylist.data.model.UserItem
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    private val _userListResponse = MutableStateFlow<Resource<UserList>>(Resource.Loading())
    val userListResponse = _userListResponse.asStateFlow()

    var userListToShow = mutableStateOf<List<UserItem>>(listOf())

    fun getUserList() {
        repository.getList().onEach { response ->
            _userListResponse.emit(response)
        }.launchIn(scope = viewModelScope)
    }
}