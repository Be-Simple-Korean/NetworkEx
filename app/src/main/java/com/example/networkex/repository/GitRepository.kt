package com.example.networkex.repository

import com.example.networkex.model.GithubResponse
import retrofit2.Call

interface GitRepository {

    /**
     * git hub 유저 조회
     */
    suspend fun getUsers(q:String) : Call<GithubResponse>
}