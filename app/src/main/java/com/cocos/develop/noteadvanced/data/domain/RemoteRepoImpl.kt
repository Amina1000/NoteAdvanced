package com.cocos.develop.noteadvanced.data.domain

import com.cocos.develop.noteadvanced.data.NoteData
import com.cocos.develop.noteadvanced.data.Token
import com.cocos.develop.noteadvanced.data.User
import com.cocos.develop.noteadvanced.data.datasource.NoteApi
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

/**
 * homework com.cocos.develop.noteadvanced.data.domain
 *
 * @author Amina
 * 29.11.2021
 */
class RemoteRepoImpl(private val noteApi: NoteApi):RemoteRepository {

    override fun getToken(user: User): Single<Token> =
        noteApi.post(user)


    override fun getNotes(): Single<List<NoteData>> {
        TODO("Not yet implemented")
    }

    override fun getFavorite(): Single<List<NoteData>> {
        TODO("Not yet implemented")
    }

    override fun putNote(noteData: NoteData): Completable {
        TODO("Not yet implemented")
    }
}