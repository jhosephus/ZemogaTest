package com.example.zamogatest.feature_posts.domain.use_case

import com.example.zamogatest.core.common.Resource
import com.example.zamogatest.feature_posts.domain.model.User
import com.example.zamogatest.feature_posts.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    operator fun invoke(userId: Int) : Flow<Resource<User>> {
        return readUserInfo(userId)
    }

    private fun readUserInfo(userId: Int) : Flow<Resource<User>> {
        return userRepository.getUser(userId)
    }

}