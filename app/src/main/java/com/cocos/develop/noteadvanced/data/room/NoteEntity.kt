package com.cocos.develop.noteadvanced.data.room


import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * homework com.cocos.develop.noteadvanced.data.room
 *
 * @author Amina
 * 23.11.2021
 */
@Entity
class NoteEntity(
    @PrimaryKey
    val id: Int,
    val name:String?,
    val description:String?,
    val date: String?,
    var favorite:Boolean = false
)