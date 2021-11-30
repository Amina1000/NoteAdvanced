package com.cocos.develop.noteadvanced.data.datasource

import com.cocos.develop.noteadvanced.data.NoteData
import com.cocos.develop.noteadvanced.data.Token
import com.cocos.develop.noteadvanced.data.User
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

/**
 * homework com.cocos.develop.noteadvanced.data.datasource
 *
 * @author Amina
 * 29.11.2021
 */
interface NoteApi {
    @Headers("Content-Type: application/json")
    @POST("/api/token/")
    fun post(@Body user: User): Single<Token>

    @GET("/api/notes/")
    fun getNotes(@Header("Authorization") access:String): Single<List<NoteData>>
}