package com.example.zamogatest.core.di

import com.example.zamogatest.BuildConfig
import com.example.zamogatest.feature_posts.data.remote.api.CommentApi
import com.example.zamogatest.feature_posts.data.remote.api.PostApi
import com.example.zamogatest.feature_posts.data.remote.api.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitCategories() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePostApi(retrofit : Retrofit) : PostApi {
        return retrofit.create(PostApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserApi(retrofit : Retrofit) : UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCommentApi(retrofit : Retrofit) : CommentApi {
        return retrofit.create(CommentApi::class.java)
    }


}