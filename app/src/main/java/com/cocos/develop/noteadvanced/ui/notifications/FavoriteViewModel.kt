package com.cocos.develop.noteadvanced.ui.notifications

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cocos.develop.noteadvanced.data.NoteData
import com.cocos.develop.noteadvanced.data.domain.LocalRepository
import com.cocos.develop.noteadvanced.rx.SchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.java.KoinJavaComponent

class FavoriteViewModel : ViewModel() {

    private val _favoriteLiveData = MutableLiveData<List<NoteData>>()
    private val favoriteLiveData: LiveData<List<NoteData>> = _favoriteLiveData

    private var currentDisposable = CompositeDisposable()
    private val schedulerProvider: SchedulerProvider = SchedulerProvider()
    private val usersRepoImpl : LocalRepository by KoinJavaComponent.inject(LocalRepository::class.java)

    fun subscribe(): LiveData<List<NoteData>> {
        return favoriteLiveData
    }

    fun getData() {
        currentDisposable.add(
            usersRepoImpl.getFavorite()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                    { noteList -> _favoriteLiveData.postValue(noteList) },
                    { error -> Log.e("Note list loader", error.message.toString()) })

        )
    }

    override fun onCleared() {
        currentDisposable.clear()
        super.onCleared()
    }
}