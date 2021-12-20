package com.cocos.develop.noteadvanced.data.domain

import com.cocos.develop.noteadvanced.data.NoteData
import com.cocos.develop.noteadvanced.data.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

/**
 * homework com.cocos.develop.noteadvanced.data.domain
 *
 * @author Amina
 * 23.11.2021
 */
interface LocalRepository {
    fun getNotes():Single<List<NoteData>>
    fun getFavorite():Single<List<NoteData>>
    fun putNote(noteData: NoteData):Completable
    fun getUsers():Single<List<User>>
    fun putUser(user:User):Completable
}