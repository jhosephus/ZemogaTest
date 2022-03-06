package com.example.zamogatest.feature_posts.data.remote.api

import com.example.zamogatest.feature_posts.data.remote.dto.PostDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApi {

    @GET("posts")
    suspend fun getAllPosts() : List<PostDto>

    @GET("posts/{postId}")
    suspend fun getPostById(@Path("postId") postId : Int) : PostDto

}