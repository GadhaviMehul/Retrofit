package com.example.retrofit4

import android.app.Application
import android.content.Context

class App:Application() {
    lateinit var repository: UserRepository

    companion object{
        private var INSTANCES:App?= null

        private fun getApplicationContext():Context{
            return INSTANCES!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCES = this
        setData()
    }
    private fun setData(){
        val apiService = RetrofitHelper.addUserData().create(ApiService::class.java)
        repository = UserRepository(apiService)


    }
}