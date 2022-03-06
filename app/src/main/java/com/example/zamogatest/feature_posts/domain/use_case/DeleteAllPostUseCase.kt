package com.example.zamogatest.feature_posts.domain.use_case

import com.example.zamogatest.core.common.Resource
import com.example.zamogatest.feature_posts.domain.model.Post
import com.example.zamogatest.feature_posts.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteAllPostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {

    operator fun invoke() : Flow<Resource<Boolean>> {
        return deleteAll()
    }

    private fun deleteAll() : Flow<Resource<Boolean>> {
        return postRepository.deleteAllPosts()
    }

}