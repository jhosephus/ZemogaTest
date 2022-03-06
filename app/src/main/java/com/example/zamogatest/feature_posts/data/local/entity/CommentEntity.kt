package com.example.zamogatest.feature_posts.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.zamogatest.feature_posts.domain.model.Comment

@Entity
data class CommentEntity(
    val postId: Int,
    @PrimaryKey val id: Int,
    val name: String,
    val email: String,
    val body: String
) {
    fun toComment() : Comment {
        return Comment(
            postId, id, name, email, body
        )
    }
}
