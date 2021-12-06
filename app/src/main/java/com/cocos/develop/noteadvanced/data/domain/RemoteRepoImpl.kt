package com.cocos.develop.noteadvanced.data.domain

import com.cocos.develop.noteadvanced.data.NoteData
import com.cocos.develop.noteadvanced.data.Token
import com.cocos.develop.noteadvanced.data.User
import com.cocos.develop.noteadvanced.data.datasource.NoteApi
import io.reactivex.rxjava3.core.Single

/**
 * homework com.cocos.develop.noteadvanced.data.domain
 *
 * @author Amina
 * 29.11.2021
 */
class RemoteRepoImpl(private val noteApi: NoteApi) : RemoteRepository {

    override fun getToken(user: User): Single<Token> =
        noteApi.post(user)


    override fun getNotes(access: String): Single<List<NoteData>> {
        return noteApi.getNotes(access)
    }


    override fun getFavorite(): Single<List<NoteData>> {
        TODO("Not yet implemented")
    }

    override fun putNote(access: String, noteData: NoteData): Single<NoteData> {
        return if (noteData.id == 1) {
            noteApi.postNote(access, noteData)
        } else {
            noteApi.putNote(noteData.id,access,noteData)
        }
    }
}