package com.example.zamogatest.feature_posts.data.remote.api

import com.example.zamogatest.feature_posts.data.remote.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id : Int) : UserDto

}