package com.cocos.develop.noteadvanced.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * homework com.cocos.develop.noteadvanced.data
 *
 * @author Amina
 * 16.11.2021
 */
@Parcelize
data class NoteData(

    val id: String,
    val userId: String,
    val name:String,
    val description:String,
    val date: String,
    var favorite:Boolean = false

):Parcelable