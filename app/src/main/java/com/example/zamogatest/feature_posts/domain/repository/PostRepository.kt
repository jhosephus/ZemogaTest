package com.example.zamogatest.feature_posts.domain.repository

import com.example.zamogatest.core.common.Resource
import com.example.zamogatest.feature_posts.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    fun getPosts(): Flow<Resource<List<Post>>>

    fun insertPost(post: Post): Flow<Resource<Post>>

    fun deletePost(post: Post): Flow<Resource<Post>>

    fun deleteAllPosts(): Flow<Resource<Boolean>>

    fun fetchAllPosts(): Flow<Resource<List<Post>>>

}