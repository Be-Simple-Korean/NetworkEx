package com.example.networkex.repository

import com.example.networkex.model.GithubResponse
import com.example.networkex.network.GitApi
import retrofit2.Call

class GitRepositoryImpl(private val gitApi: GitApi) : GitRepository {
    override suspend fun getUsers(q: String): Call<GithubResponse> {
        return gitApi.getUsers(q)
    }
}