package com.example.retrofit4

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {
    @Headers("app-id:6575543ef4b652e6dee6df65")
    @GET("user")
    suspend fun getUserData(): Response<UserData>

   /* @GET("user/{id}")
    suspend fun getUserDetails(@Path("id") userId: String): Response<UserDetails>*/

    @Headers("app-id: 6575543ef4b652e6dee6df65") // Replace "Your-App-ID" with your actual app ID
    @GET("user/{id}")
    suspend fun getUserDetails(@Path("id") userId: String): Response<UserDetails>

}