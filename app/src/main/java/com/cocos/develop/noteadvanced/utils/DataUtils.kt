package com.cocos.develop.noteadvanced.utils

import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.res.Resources
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import com.cocos.develop.noteadvanced.R
import com.cocos.develop.noteadvanced.data.NoteData
import com.cocos.develop.noteadvanced.data.User
import com.cocos.develop.noteadvanced.data.room.NoteEntity
import com.cocos.develop.noteadvanced.data.room.UserEntity
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * homework com.cocos.develop.noteadvanced.utils
 *
 * @author Amina
 * 22.11.2021
 */

const val TOKEN = "TOKEN"

fun noteDefault(): NoteData {
    return NoteData(1, "", "", getDate(Calendar.getInstance().time))
}

fun noteEntityListMap(notes: List<NoteEntity>) =
    notes.map {
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

fun userEntityListMap(users: List<UserEntity>) =
    users.map {
        userEntityMap(it)
    }

fun makeErrorToast(context: Context?, message: String?) {
    message?.let {
        val errorMsg = context?.resources?.getString(R.string.error_message) + "\n" + it
        Toast.makeText(
            context,
            errorMsg,
            Toast.LENGTH_LONG
        ).show()
    }
}


fun userEntityMap(userEntity: UserEntity) = User(
    userEntity.id,
    userEntity.email,
    "",
    userEntity.name,
    userEntity.lastName
)

fun userDataMap(user: User) = UserEntity(
    user.id,
    user.email,
    user.name,
    user.lastName
)

fun openScreen(activity: Activity, target: Int, bundle: Bundle? = null) {
    Navigation.findNavController(activity, R.id.nav_host_fragment_activity_main).also { nav ->
        bundle?.let {
            nav.navigate(target, bundle)
        } ?: nav.navigate(target)

    }
}

fun readPrefAccess(context: Context?): String? {
    // Специальный класс для хранения настроек
    val sharedPref = context?.getSharedPreferences(TOKEN, MODE_PRIVATE)
    // Считываем значения настроек
    return sharedPref?.getString("access", null)
}

fun getDate(date: Date): String {
    var formatted = date.toString()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        val format1 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
        formatted = format1.format(date)
    }
    return formatted
}

fun parsDate(formatted: String): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val parsedDate =
            LocalDate.parse(formatted, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
        val dTF = DateTimeFormatter.ofPattern("dd MMMM uuuu")
        dTF.format(parsedDate)
    } else {
        formatted
    }
}

fun View.showViewWorking() {
    this.visibility = View.GONE
}

fun View.showViewLoading() {
    this.visibility = View.VISIBLE
}
