package com.example.zamogatest.feature_posts.domain.use_case

import com.example.zamogatest.core.common.Resource
import com.example.zamogatest.feature_posts.domain.model.Comment
import com.example.zamogatest.feature_posts.domain.repository.CommentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadCommentsUseCase @Inject constructor(
    private val commentRepository: CommentRepository
) {

    operator fun invoke(postId: Int) : Flow<Resource<List<Comment>>> {
        return readComments(postId)
    }

    private fun readComments(postId: Int) : Flow<Resource<List<Comment>>> {
        return commentRepository.getCommentsOfPost(postId)
    }

}