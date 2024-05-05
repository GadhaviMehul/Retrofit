package com.example.retrofit4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class UserDetailRepository(private val apiService: ApiService,private val userId:String) {
    private var userDetailsMutableData = MutableLiveData<MutableList<UserDetails>>()

    val userDetails:LiveData<MutableList<UserDetails>>
    get() = userDetailsMutableData

    suspend fun getUserDetails(){
        val result = apiService.getUserDetails(userId)
        if (result.body()!=null){
            userDetailsMutableData.postValue(mutableListOf(result.body()!!) )
        }
    }
}