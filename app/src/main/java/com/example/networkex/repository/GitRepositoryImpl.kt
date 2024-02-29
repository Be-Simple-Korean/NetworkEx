package com.example.networkex.repository

import android.util.Log
import com.example.networkex.model.GithubResponse
import com.example.networkex.network.GitApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class GitRepositoryImpl(private val gitApi: GitApi) : GitRepository {
    override suspend fun getUsers(q: String): Flow<Response<GithubResponse>> = flow {
        val response = gitApi.getUsers(q)
        if (response.isSuccessful) {
            emit(gitApi.getUsers(q))
        } else {
            Log.e("Impl", response.errorBody().toString())
        }
    }
}