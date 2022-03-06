package com.example.zamogatest.feature_posts.domain.model

import com.example.zamogatest.feature_posts.data.local.entity.CommentEntity

data class Comment(
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
}
