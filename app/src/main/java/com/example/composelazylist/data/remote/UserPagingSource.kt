package com.example.composelazylist.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.composelazylist.data.model.UserItem
import com.example.composelazylist.data.repo.MainRepository

class UserPagingSource(
    private val mainRepository: MainRepository
) : PagingSource<Int, UserItem>() {
    override fun getRefreshKey(state: PagingState<Int, UserItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserItem> {
        val currentPageKey = params.key ?: 1
        val result = mainRepository.getList(currentPageKey, params.loadSize)
        return LoadResult.Page(
            data = result.data?.userList ?: listOf(),
            prevKey = if (currentPageKey == 1) null else currentPageKey.minus(1),
            nextKey = if (result.data?.userList.isNullOrEmpty()) null else currentPageKey.plus(
                1
            ),
        )
    }
}