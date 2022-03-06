package com.example.zamogatest.feature_posts.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.zamogatest.feature_posts.data.local.dao.CommentDao
import com.example.zamogatest.feature_posts.data.local.dao.PostDao
import com.example.zamogatest.feature_posts.data.local.dao.UserDao
import com.example.zamogatest.feature_posts.data.local.entity.CommentEntity
import com.example.zamogatest.feature_posts.data.local.entity.PostEntity
import com.example.zamogatest.feature_posts.data.local.entity.UserEntity

@Database(
    entities = [PostEntity::class,
               UserEntity::class,
               CommentEntity::class],
    version = 1,
)
@TypeConverters(Converters::class)
abstract class PostDatabase : RoomDatabase() {

    abstract val postDao: PostDao
    abstract val userDao: UserDao
    abstract val commentDao: CommentDao

}