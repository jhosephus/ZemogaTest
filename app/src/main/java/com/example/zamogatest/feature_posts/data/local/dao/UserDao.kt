package com.example.zamogatest.feature_posts.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.zamogatest.feature_posts.data.local.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM userentity WHERE id = :userId")
    suspend fun getUserById(userId: Int) : UserEntity?

}