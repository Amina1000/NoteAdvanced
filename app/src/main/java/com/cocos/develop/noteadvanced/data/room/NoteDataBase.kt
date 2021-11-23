package com.cocos.develop.noteadvanced.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * homework com.cocos.develop.noteadvanced.data.room
 *
 * @author Amina
 * 23.11.2021
 */
@Database(entities = arrayOf(NoteEntity::class), version = 1, exportSchema = false)
abstract class NoteDataBase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}