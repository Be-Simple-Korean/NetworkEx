package com.example.networkex.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.networkex.model.UserVO
import com.example.networkex.repository.GitRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val gitRepositoryImpl: GitRepositoryImpl) :
    ViewModel() {
    private var inUserList = mutableListOf<UserVO>()
    private val _userList = MutableStateFlow<List<UserVO>>(inUserList)
    val userList: StateFlow<List<UserVO>> = _userList.asStateFlow()

    /**
     * 유저 목록 조회
     */
    fun getUsers(q: String) {
        CoroutineScope(Dispatchers.IO).launch {
            gitRepositoryImpl.getUsers(q).catch { exception ->
                Log.e("ERROR!!", exception.message.toString())
            }.collectLatest { response ->
                Log.e("RES", response.body().toString())
                if (response.isSuccessful) {
                    inUserList = response.body()?.items?.toMutableList()
                        ?: listOf<UserVO>().toMutableList()
                } else {
                    inUserList = emptyList<UserVO>().toMutableList()
                }
                withContext(Dispatchers.Main) {
                    _userList.value = inUserList
                }
            }
        }
    }
}