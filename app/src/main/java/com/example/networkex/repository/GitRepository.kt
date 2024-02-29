package com.example.networkex.repository

import com.example.networkex.model.GithubResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface GitRepository {

    /**
     * git hub 유저 조회
     */
    suspend fun getUsers(q:String) : Flow<Response<GithubResponse>>
}