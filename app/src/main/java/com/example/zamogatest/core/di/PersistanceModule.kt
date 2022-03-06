package com.example.zamogatest.core.di

import android.app.Application
import androidx.room.Room
import com.example.zamogatest.feature_posts.data.local.Converters
import com.example.zamogatest.feature_posts.data.local.PostDatabase
import com.example.zamogatest.feature_posts.data.util.GsonParser
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistanceModule {

    @Provides
    @Singleton
    fun provideCacheDatabase(app: Application) : PostDatabase {
        return Room.databaseBuilder(
            app, PostDatabase::class.java, "post_db"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

}