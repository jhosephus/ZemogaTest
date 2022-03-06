package com.example.zamogatest.feature_posts.domain.use_case

import com.example.zamogatest.core.common.Resource
import com.example.zamogatest.feature_posts.domain.model.Post
import com.example.zamogatest.feature_posts.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ReadPostsUseCase @Inject constructor(
    private val postRepository: PostRepository
) {

    operator fun invoke() : Flow<Resource<List<Post>>> {
        return readPosts().map {
            when(it) {
                is Resource.Success -> {
                    Resource.Success(data = orderPostsFavourites(it.data!!))
                }
                else -> {
                    it
                }
            }
        }
    }

    private fun readPosts() : Flow<Resource<List<Post>>> {
        return postRepository.getPosts()
    }

    private fun orderPostsFavourites(posts: List<Post>): List<Post>{
        return posts.sortedByDescending { it.isFavourite }
    }

}