package com.cocos.develop.noteadvanced

import android.app.Application
import com.cocos.develop.noteadvanced.data.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * homework com.cocos.develop.noteadvanced
 *
 * @author Amina
 * 24.11.2021
 */
class NoteApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            // declare used Android context
            androidContext(this@NoteApp)
            // declare modules
            modules(application, apiModule, repoModule, homeScreen, detailScreen,favoriteScreen)
        }
    }
}