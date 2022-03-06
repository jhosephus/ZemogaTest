package com.example.zamogatest.feature_posts.data.remote.dto

import com.example.zamogatest.feature_posts.data.local.entity.CommentEntity
import com.example.zamogatest.feature_posts.domain.model.Comment

data class CommentDto(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
) {
    fun toCommentEntity() : CommentEntity {
        return CommentEntity(
            postId, id, name, email, body
        )
    }

    fun toComment() : Comment {
        return Comment(
            postId, id, name, email, body
        )
    }
}
