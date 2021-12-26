package com.cocos.develop.noteadvanced.data.domain

import com.cocos.develop.noteadvanced.data.NoteData
import com.cocos.develop.noteadvanced.data.Token
import com.cocos.develop.noteadvanced.data.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

/**
 * homework com.cocos.develop.noteadvanced.data.domain
 *
 * @author Amina
 * 29.11.2021
 */
interface RemoteRepository {
    fun getToken(user: User): Single<Token>
    fun getNotes(access:String): Single<List<NoteData>>
    fun getFavorite(access:String): Single<List<NoteData>>
    fun putNote(access:String,noteData: NoteData): Single<NoteData>
    fun putUser(access: String?,user: User):Single<User>
    fun getUser(id:Int, access: String):Single<User>
    fun getUserId(access: String):Single<List<User>>
}