package com.example.networkex.network

import com.example.networkex.model.GithubResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitApi {

    // 사용자 조회
    @GET("search/users")
    suspend fun getUsers(
        @Query("q") q: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ) : Response<GithubResponse>
}