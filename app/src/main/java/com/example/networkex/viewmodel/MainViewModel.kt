package com.example.networkex.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.networkex.model.GithubResponse
import com.example.networkex.model.UserVO
import com.example.networkex.repository.GitRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val gitRepositoryImpl: GitRepositoryImpl) :
    ViewModel() {
    private var inUserList = mutableListOf<UserVO>()
    private val _userList = MutableLiveData<List<UserVO>>(emptyList())
    val userList: LiveData<List<UserVO>> = _userList

    /**
     * 유저 목록 조회
     */
    fun getUsers(q: String) {
        CoroutineScope(Dispatchers.IO).launch {
            gitRepositoryImpl.getUsers(q).enqueue(object : Callback<GithubResponse> {
                override fun onResponse(
                    call: Call<GithubResponse>,
                    response: Response<GithubResponse>
                ) {
                    response.body()?.let { body ->
                        inUserList = body.items.toMutableList()
                        _userList.value = inUserList
                    }
                }

                override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                    Log.e("onFail", "${t.message.toString()}")
                }
            })
        }
    }
}