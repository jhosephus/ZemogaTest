package com.example.zamogatest.feature_posts.data.repository

import android.util.Log
import com.example.zamogatest.core.common.Resource
import com.example.zamogatest.feature_posts.data.local.dao.CommentDao
import com.example.zamogatest.feature_posts.data.remote.api.CommentApi
import com.example.zamogatest.feature_posts.domain.model.Comment
import com.example.zamogatest.feature_posts.domain.repository.CommentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CommentRepositoryImpl (
    private val api: CommentApi,
    private val dao: CommentDao
) : CommentRepository {
    override fun getCommentsOfPost(postId: Int): Flow<Resource<List<Comment>>> {
        return return flow {
            emit(Resource.Loading<List<Comment>>()) //Start loading
            val commentEntity = dao.getCommentByPostId(postId = postId)
            if (!commentEntity.isEmpty()) {
                emit(Resource.Success(data = commentEntity.map { it.toComment() }))
            } else {
                try {
                    val commentDto = api.getCommentByPostId(postId = postId)
                    dao.insertComments(commentDto.map { it.toCommentEntity() })
                    emit(Resource.Success(data = commentDto.map { it.toComment() }))
                } catch (e : HttpException){
                    emit(Resource.Error<List<Comment>>(
                        message = "Something went wrong!",
                        data = emptyList()
                    ))
                } catch (e : IOException) {
                    emit(Resource.Error<List<Comment>>(
                        message = "Unable to reach the server",
                        data = emptyList()
                    ))
                }
            }
        }
    }
}