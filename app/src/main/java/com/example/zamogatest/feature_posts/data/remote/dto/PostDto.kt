package com.example.zamogatest.feature_posts.data.remote.dto

import com.example.zamogatest.feature_posts.data.local.entity.PostEntity
import com.example.zamogatest.feature_posts.domain.model.Post

data class PostDto(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
){
    fun toPost() : Post{
        return Post(
            userId = userId,
            id = id,
            title = title,
            body = body,
            isFavourite = 0
        )
    }

    fun toPostEntity() : PostEntity{
        return PostEntity(
            userId = userId,
            id = id,
            title = title,
            body = body,
            isFavourite = 0
        )
    }
}