package com.example.composelazylist.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.composelazylist.data.model.UserItem
import com.example.composelazylist.data.network.ErrorType
import com.example.composelazylist.data.remote.UserPagingSource
import com.example.composelazylist.data.repo.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {

    var selectedUserItem: UserItem? = null
    val loadUserData = Pager(
        config = PagingConfig(
            pageSize = 10,
            prefetchDistance = 3,
            initialLoadSize = 20
        ),
        pagingSourceFactory = { UserPagingSource(repository) }
    ).flow.flowOn(Dispatchers.IO).catch {
        ErrorType.UNKNOWN
    }.cachedIn(viewModelScope)
}