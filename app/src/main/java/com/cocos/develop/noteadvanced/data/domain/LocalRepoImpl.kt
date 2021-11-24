package com.cocos.develop.noteadvanced.data.domain

import com.cocos.develop.noteadvanced.data.NoteData
import com.cocos.develop.noteadvanced.data.room.NoteDataBase
import com.cocos.develop.noteadvanced.utils.noteDataMap
import com.cocos.develop.noteadvanced.utils.noteEntityListMap
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

/**
 * homework com.cocos.develop.noteadvanced.data.domain
 *
 * @author Amina
 * 23.11.2021
 */
class LocalRepoImpl(db: NoteDataBase):LocalRepository {

    private val noteDao = db.noteDao()

    override fun getNotes(): Single<List<NoteData>> {
      return noteDao.all().map {
          noteEntityListMap(it)
      }
    }

    override fun getFavorite(): Single<List<NoteData>> {
        return noteDao.getFavorite().map {
            noteEntityListMap(it)
        }
    }

    override fun putNote(noteData: NoteData): Completable {
        return noteDao.insert(noteDataMap(noteData))
    }
}