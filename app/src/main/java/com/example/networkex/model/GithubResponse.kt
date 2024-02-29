package com.example.networkex.model

data class GithubResponse(
    val incomplete_results: Boolean,
    val items: List<UserVO>,
    val total_count: Int
)