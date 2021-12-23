package com.cocos.develop.noteadvanced.data.domain


/**
 * homework com.cocos.develop.dictionarykiss.domain
 *
 * @author Amina
 * 26.08.2021
 */
sealed class AppState {
    data class Success<T>(val data:T?) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}