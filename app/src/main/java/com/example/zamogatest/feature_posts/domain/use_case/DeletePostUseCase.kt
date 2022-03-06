package com.example.zamogatest.feature_posts.domain.use_case

import com.example.zamogatest.core.common.Resource
import com.example.zamogatest.feature_posts.domain.model.Post
import com.example.zamogatest.feature_posts.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeletePostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {

    operator fun invoke(post: Post) : Flow<Resource<Post>> {
        return deletePost(post)
    }

    private fun deletePost(post: Post) : Flow<Resource<Post>> {
        return postRepository.deletePost(post)
    }

}