package com.cocos.develop.noteadvanced.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * homework com.cocos.develop.noteadvanced.data
 *
 * @author Amina
 * 29.11.2021
 */
@Parcelize
data class User (
    @field:SerializedName("id") var id:Int,
    @field:SerializedName("email") val email:String,
    @field:SerializedName("password") var password: String,
    @field:SerializedName("name") var name:String?,
    @field:SerializedName("last_name") var lastName:String?
): Parcelable