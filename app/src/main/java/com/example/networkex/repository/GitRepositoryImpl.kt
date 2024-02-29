package com.example.networkex.repository

import com.example.networkex.model.GithubResponse
import com.example.networkex.network.GitApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GitRepositoryImpl(private val gitApi: GitApi) : GitRepository {
    override suspend fun getUsers(q: String): Flow<GithubResponse> = flow{
        emit(gitApi.getUsers(q))
    }
}