package com.example.zamogatest.feature_posts.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.zamogatest.feature_posts.domain.model.Post

@Entity
data class PostEntity(
    @PrimaryKey val id: Int,
    val userId: Int,
    val title: String,
    val body: String,
    val isFavourite: Int
){
    fun toPost() : Post {
        return Post(
            userId = userId,
            id = id,
            title = title,
            body = body,
            isFavourite = isFavourite
        )
    }
}