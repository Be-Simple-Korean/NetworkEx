package com.example.networkex.network

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.networkex.model.UserVO
import retrofit2.HttpException
import java.io.IOException

class GithubPagingSource(
    private val service: GitApi,
    private val query: String
) : PagingSource<Int, UserVO>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserVO> {
        Log.e("tag", "load")
        val position = params.key ?: 1

        return try {
            Log.e("tag", "request - $query, $position, ${params.loadSize}")
            val response = service.getUsers(query, position, params.loadSize)
            val users = response.body()?.items ?: emptyList()
            val nextKey = if (users.isEmpty()) {
                null
            } else {
                position + (params.loadSize / 20)
            }

            LoadResult.Page(
                data = users,
                prevKey = if (position == 1) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserVO>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}