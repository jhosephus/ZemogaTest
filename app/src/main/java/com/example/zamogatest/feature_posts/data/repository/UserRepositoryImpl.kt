package com.example.zamogatest.feature_posts.data.repository

import com.example.zamogatest.core.common.Resource
import com.example.zamogatest.feature_posts.data.local.dao.UserDao
import com.example.zamogatest.feature_posts.data.remote.api.UserApi
import com.example.zamogatest.feature_posts.domain.model.Post
import com.example.zamogatest.feature_posts.domain.model.User
import com.example.zamogatest.feature_posts.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl (
    private val api: UserApi,
    private val dao: UserDao
) : UserRepository {
    override fun getUser(userId: Int): Flow<Resource<User>> {
        return return flow {
            emit(Resource.Loading<User>()) //Start loading
            val userEntity = dao.getUserById(userId = userId)
            if (userEntity != null) {
                emit(Resource.Success(data = userEntity.toUser()))
            } else {
                try {
                    val userDto = api.getUserById(userId)
                    dao.insertUser(userDto.toUserEntity())
                    emit(Resource.Success(data = userDto.toUser()))
                } catch (e : HttpException){
                    emit(Resource.Error<User>(
                        message = "Something went wrong!"
                    ))
                } catch (e : IOException) {
                    emit(Resource.Error<User>(
                        message = "Unable to reach the server"
                    ))
                }
            }
        }
    }
}