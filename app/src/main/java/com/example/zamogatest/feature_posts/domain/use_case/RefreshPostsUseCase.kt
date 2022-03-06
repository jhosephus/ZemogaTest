package com.example.zamogatest.feature_posts.domain.use_case

import com.example.zamogatest.core.common.Resource
import com.example.zamogatest.feature_posts.domain.model.Post
import com.example.zamogatest.feature_posts.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RefreshPostsUseCase @Inject constructor(
    private val postRepository: PostRepository
) {

    operator fun invoke() : Flow<Resource<List<Post>>> {
        return readPosts()
    }

    private fun readPosts() : Flow<Resource<List<Post>>> {
        return postRepository.fetchAllPosts()
    }

}