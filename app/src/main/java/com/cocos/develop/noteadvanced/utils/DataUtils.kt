package com.cocos.develop.noteadvanced.utils

import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import androidx.navigation.Navigation
import com.cocos.develop.noteadvanced.R
import com.cocos.develop.noteadvanced.data.NoteData
import com.cocos.develop.noteadvanced.data.Token
import com.cocos.develop.noteadvanced.data.room.NoteEntity

/**
 * homework com.cocos.develop.noteadvanced.utils
 *
 * @author Amina
 * 22.11.2021
 */

const val TOKEN = "TOKEN"

fun noteDefault(): NoteData {
    return NoteData(1, "", "", "")
}

fun noteEntityListMap(users: List<NoteEntity>) =
    users.map {
        noteEntityMap(it)
    }

fun noteEntityMap(noteEntity: NoteEntity) = NoteData(
    noteEntity.id,
    noteEntity.name,
    noteEntity.description,
    noteEntity.date,
    noteEntity.favorite
)

fun noteDataMap(noteData: NoteData) = NoteEntity(
    noteData.id,
    noteData.name,
    noteData.description,
    noteData.date,
    noteData.favorite
)

fun openScreen(activity: Activity, target: Int,bundle: Bundle?=null){
    Navigation.findNavController(activity, R.id.nav_host_fragment_activity_main).also { nav->
        bundle?.let{
            nav.navigate(target,bundle)
        }?:nav.navigate(target)

    }
}

fun readPrefAccess(context: Context?):String?{
    // Специальный класс для хранения настроек
    val sharedPref = context?.getSharedPreferences(TOKEN, MODE_PRIVATE);
    // Считываем значения настроек
    return sharedPref?.getString("access",null)
}