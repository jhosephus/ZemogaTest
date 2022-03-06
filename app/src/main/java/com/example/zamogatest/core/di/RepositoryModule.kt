package com.example.zamogatest.core.di

import com.example.zamogatest.feature_posts.data.local.PostDatabase
import com.example.zamogatest.feature_posts.data.local.dao.PostDao
import com.example.zamogatest.feature_posts.data.remote.api.CommentApi
import com.example.zamogatest.feature_posts.data.remote.api.PostApi
import com.example.zamogatest.feature_posts.data.remote.api.UserApi
import com.example.zamogatest.feature_posts.data.repository.CommentRepositoryImpl
import com.example.zamogatest.feature_posts.data.repository.PostRepositoryImpl
import com.example.zamogatest.feature_posts.data.repository.UserRepositoryImpl
import com.example.zamogatest.feature_posts.domain.repository.CommentRepository
import com.example.zamogatest.feature_posts.domain.repository.PostRepository
import com.example.zamogatest.feature_posts.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePostRepository(api : PostApi, db: PostDatabase) : PostRepository {
        return PostRepositoryImpl(api = api, dao = db.postDao)
    }

    @Provides
    @Singleton
    fun provideUserRepository(api : UserApi, db: PostDatabase) : UserRepository {
        return UserRepositoryImpl(api = api, dao = db.userDao)
    }

    @Provides
    @Singleton
    fun provideCommentRepository(api : CommentApi, db: PostDatabase) : CommentRepository {
        return CommentRepositoryImpl(api = api, dao = db.commentDao)
    }

}