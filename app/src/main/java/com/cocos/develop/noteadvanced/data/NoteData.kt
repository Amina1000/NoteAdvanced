package com.cocos.develop.noteadvanced.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * homework com.cocos.develop.noteadvanced.data
 *
 * @author Amina
 * 16.11.2021
 */
@Parcelize
data class NoteData(
    @field:SerializedName("id") var id: Int,
    @field:SerializedName("title") var name:String?,
    @field:SerializedName("text") var description:String?,
    @field:SerializedName("created") var date: String?,
    @field:SerializedName("is_active") var active:Boolean = true,
    @field:SerializedName("is_favorites") var favorite:Boolean = false
):Parcelable