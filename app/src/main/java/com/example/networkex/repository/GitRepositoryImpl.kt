package com.example.networkex.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.networkex.model.GithubResponse
import com.example.networkex.model.UserVO
import com.example.networkex.network.GitApi
import com.example.networkex.network.GithubPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class GitRepositoryImpl(private val gitApi: GitApi) : GitRepository {
//    override fun getUsers(q: String): Flow<Response<GithubResponse>> = flow {
//        val response = gitApi.getUsers(q)
//        if (response.isSuccessful) {
//            emit(response)
//        } else {
//            Log.e("Impl", response.errorBody().toString())
//        }
//    }.catch {
//        emit(Response.error(400, ResponseBody.create(null, "")))
//    }

    override fun getUsers(q: String): Flow<PagingData<UserVO>>
        {   return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = { GithubPagingSource(gitApi, q) }
        ).flow}

}