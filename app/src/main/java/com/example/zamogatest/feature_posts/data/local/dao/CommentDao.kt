package com.example.zamogatest.feature_posts.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.zamogatest.feature_posts.data.local.entity.CommentEntity
import com.example.zamogatest.feature_posts.data.local.entity.UserEntity

@Dao
interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(comments: List<CommentEntity>)

    @Query("SELECT * FROM commententity WHERE postId = :postId")
    suspend fun getCommentByPostId(postId: Int) : List<CommentEntity>

}