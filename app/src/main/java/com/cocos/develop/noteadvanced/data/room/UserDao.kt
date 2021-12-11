package com.cocos.develop.noteadvanced.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

/**
 * homework com.cocos.develop.noteadvanced.data.room
 *
 * @author Amina
 * 11.12.2021
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM UserEntity")
    fun getUser(): Single<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserEntity): Completable
}