package com.cocos.develop.noteadvanced.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * homework com.cocos.develop.noteadvanced.data.room
 *
 * @author Amina
 * 11.12.2021
 */
@Entity
class UserEntity (
    @PrimaryKey
    val id:Int,
    val email:String,
    val name:String?,
    val lastName:String?
)