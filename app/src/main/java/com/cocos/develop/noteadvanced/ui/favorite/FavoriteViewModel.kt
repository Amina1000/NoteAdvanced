package com.cocos.develop.noteadvanced.ui.favorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cocos.develop.noteadvanced.data.domain.AppState
import com.cocos.develop.noteadvanced.data.domain.LocalRepository
import com.cocos.develop.noteadvanced.data.domain.RemoteRepository
import com.cocos.develop.noteadvanced.rx.SchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.java.KoinJavaComponent

class FavoriteViewModel : ViewModel() {

    private val _favoriteLiveData = MutableLiveData<AppState>()
    private val favoriteLiveData: LiveData<AppState> = _favoriteLiveData

    private var currentDisposable = CompositeDisposable()
    private val schedulerProvider: SchedulerProvider = SchedulerProvider()
    private val usersRepoLocalImpl : LocalRepository by KoinJavaComponent.inject(LocalRepository::class.java)
    private val usersRepoRemoteImpl : RemoteRepository by KoinJavaComponent.inject(RemoteRepository::class.java)

    fun subscribe(): LiveData<AppState> {
        return favoriteLiveData
    }

    fun getData(access:String?) {
        currentDisposable.add(
            when (access) {
                null -> {
                    usersRepoLocalImpl.getFavorite()
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe(
                            { noteList -> _favoriteLiveData.postValue(AppState.Success(noteList)) },
                            { error ->
                                _favoriteLiveData.postValue(AppState.Error(error))
                                Log.e("Note list loader", error.message.toString()) })

                }
                else -> {
                    usersRepoRemoteImpl.getFavorite(access)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .subscribe(
                            { noteList -> _favoriteLiveData.postValue(AppState.Success(noteList)) },
                            { error ->
                                _favoriteLiveData.postValue(AppState.Error(error))
                                Log.e("Note list loader", error.message.toString()) })

                }
            }
        )
    }

    override fun onCleared() {
        currentDisposable.clear()
        super.onCleared()
    }
}