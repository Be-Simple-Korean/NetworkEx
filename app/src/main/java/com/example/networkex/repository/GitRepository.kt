package com.example.networkex.repository

import androidx.paging.PagingData
import com.example.networkex.model.UserVO
import kotlinx.coroutines.flow.Flow

interface GitRepository {

    /**
     * git hub 유저 조회
     */
    fun getUsers(q:String) : Flow<PagingData<UserVO>>
}