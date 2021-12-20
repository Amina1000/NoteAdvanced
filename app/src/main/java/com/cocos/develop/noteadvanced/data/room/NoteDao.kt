package com.cocos.develop.noteadvanced.data.room

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

/**
 * homework com.cocos.develop.noteadvanced.data.room
 *
 * @author Amina
 * 23.11.2021
 */
@Dao
interface NoteDao {
    @Query("SELECT * FROM NoteEntity")
    fun all(): Single<List<NoteEntity>>

    @Query("SELECT * FROM NoteEntity WHERE id LIKE :id")
    fun getDataById(id: Int): Single<NoteEntity>

    @Query("SELECT * FROM NoteEntity WHERE favorite = 1")
    fun getFavorite():  Single<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: NoteEntity):Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(entities: List<NoteEntity>):Completable

    @Update
    fun update(entity: NoteEntity):Completable

    @Delete
    fun delete(entity: NoteEntity):Completable
}