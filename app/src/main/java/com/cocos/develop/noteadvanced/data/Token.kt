package com.cocos.develop.noteadvanced.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * homework com.cocos.develop.noteadvanced
 *
 * @author Amina
 * 29.11.2021
 */
@Parcelize
data class Token(
    @field:SerializedName("refresh") val refresh: String,
    @field:SerializedName("access") val access: String
) : Parcelable