package com.cocos.develop.noteadvanced.utils

import com.cocos.develop.noteadvanced.data.NoteData
import com.cocos.develop.noteadvanced.data.room.NoteEntity

/**
 * homework com.cocos.develop.noteadvanced.utils
 *
 * @author Amina
 * 22.11.2021
 */

fun noteDefault(): NoteData {
    return NoteData("", "", "", "", "")
}

fun noteEntityListMap(users: List<NoteEntity>) =
    users.map {
        noteEntityMap(it)
    }

fun noteEntityMap(noteEntity: NoteEntity) = NoteData(
    noteEntity.id,
    noteEntity.userId,
    noteEntity.name,
    noteEntity.description,
    noteEntity.date,
    noteEntity.favorite
)

fun noteDataMap(noteData: NoteData) = NoteEntity(
    noteData.id,
    noteData.userId,
    noteData.name,
    noteData.description,
    noteData.date,
    noteData.favorite
)
