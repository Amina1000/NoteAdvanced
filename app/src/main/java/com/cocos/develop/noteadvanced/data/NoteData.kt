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
    var id: String,
    val userId: String?,
    var name:String?,
    var description:String?,
    var date: String?,
    var favorite:Boolean = false
):Parcelable