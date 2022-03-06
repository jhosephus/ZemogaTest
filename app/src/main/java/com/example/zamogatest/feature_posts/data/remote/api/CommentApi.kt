package com.example.zamogatest.feature_posts.data.remote.api

import com.example.zamogatest.feature_posts.data.remote.dto.CommentDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentApi {

    @GET("posts/{id}/comments")
    suspend fun getCommentByPostId(@Path("id") postId : Int) : List<CommentDto>
}