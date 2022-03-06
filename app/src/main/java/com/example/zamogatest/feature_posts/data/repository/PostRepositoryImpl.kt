package com.example.zamogatest.feature_posts.data.repository

import com.example.zamogatest.core.common.Resource
import com.example.zamogatest.feature_posts.data.local.dao.PostDao
import com.example.zamogatest.feature_posts.data.remote.api.PostApi
import com.example.zamogatest.feature_posts.domain.model.Post
import com.example.zamogatest.feature_posts.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class PostRepositoryImpl (
    private val api: PostApi,
    private val dao: PostDao
) : PostRepository {
    override fun getPosts(): Flow<Resource<List<Post>>> {
        return flow {
            emit(Resource.Loading()) //Start loading
            val postsEntity = dao.getAllPosts()
            var posts = emptyList<Post>()
            if (!postsEntity.isEmpty()) {
                posts = postsEntity.map { it.toPost() }
                emit(Resource.Success(data = posts))
            } else {
                try {
                    val postsDto = api.getAllPosts()
                    dao.insertPosts(postsDto.map { it.toPostEntity() })
                    emit(Resource.Success(data = postsDto.map { it.toPost() }))
                } catch (e : HttpException){
                    emit(Resource.Error(
                        message = "Something went wrong!",
                        data = posts
                    ))
                } catch (e : IOException) {
                    emit(Resource.Error(
                        message = "Unable to reach the server",
                        data = posts
                    ))
                }
            }
        }
    }

    override fun insertPost(post: Post): Flow<Resource<Post>> {
        return flow{
            try {
                emit(Resource.Loading<Post>())
                dao.insertPost(post = post.toPostEntity())
                emit(Resource.Success(data = post))
            } catch (e : Exception) {
                emit(Resource.Error<Post>(message = "Failed to do operation"))
            }
        }
    }

    override fun deletePost(post: Post): Flow<Resource<Post>> {
        return flow{
            try {
                emit(Resource.Loading<Post>())
                dao.deletePost(postId = post.id)
                emit(Resource.Success(data = post))
            } catch (e : Exception) {
                emit(Resource.Error<Post>(message = "Failed to do operation"))
            }
        }
    }

    override fun deleteAllPosts(): Flow<Resource<Boolean>> {
        return flow{
            try {
                emit(Resource.Loading())
                dao.deleteAllPosts()
                emit(Resource.Success<Boolean>(data = true))
            } catch (e : Exception) {
                emit(Resource.Error<Boolean>(message = "Failed to do operation"))
            }
        }
    }

    override fun fetchAllPosts(): Flow<Resource<List<Post>>> {
        return flow{
            try {
                emit(Resource.Loading<List<Post>>())
                val postsDto = api.getAllPosts()
                dao.insertPosts(postsDto.map { it.toPostEntity() })
                emit(Resource.Success(data = postsDto.map { it.toPost() }))
            } catch (e : HttpException){
                emit(Resource.Error<List<Post>>(
                    message = "Something went wrong!",
                    data = emptyList()
                ))
            } catch (e : IOException) {
                emit(Resource.Error<List<Post>>(
                    message = "Unable to reach the server",
                    data = emptyList()
                ))
            }
        }
    }

}