package com.example.zamogatest.feature_posts.domain.repository

import com.example.zamogatest.core.common.Resource
import com.example.zamogatest.feature_posts.domain.model.Comment
import kotlinx.coroutines.flow.Flow

interface CommentRepository {

    fun getCommentsOfPost(postId: Int) : Flow<Resource<List<Comment>>>

}