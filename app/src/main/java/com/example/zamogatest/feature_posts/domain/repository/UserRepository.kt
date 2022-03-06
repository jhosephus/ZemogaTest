package com.example.zamogatest.feature_posts.domain.repository

import com.example.zamogatest.core.common.Resource
import com.example.zamogatest.feature_posts.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUser(userId: Int): Flow<Resource<User>>
}