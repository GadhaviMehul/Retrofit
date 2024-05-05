package com.example.retrofit4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class UserRepository(private val apiService: ApiService) {

    private var userMutableData = MutableLiveData<MutableList<UserData>>()

    val userData: LiveData<MutableList<UserData>>
        get() = userMutableData

    suspend fun getUserData() {
        val result = apiService.getUserData()
        if (result.body() != null) {
            userMutableData.postValue(mutableListOf(result.body()!!))
        }
    }
}