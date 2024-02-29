package com.example.networkex.network

import com.example.networkex.model.GithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitApi {

    // 사용자 조회
    @GET("search/users")
    suspend fun getUsers(
        @Query("q") q: String,
    ) : Call<GithubResponse>
}