package com.example.challengechapterlima.service

import com.example.challengechapterlima.model.ResponseDataUserItem
import com.example.challengechapterlima.model.User
import retrofit2.Call
import retrofit2.http.*

interface ApiUser {
    @GET("user")
    fun getAllUser() : Call<List<ResponseDataUserItem>>

    @POST("user")
    fun addUser(@Body request : User) : Call<ResponseDataUserItem>

    @PUT("user/{id}")
    fun putUser(@Path("id") id:Int, @Body request: User) : Call<List<ResponseDataUserItem>>
}