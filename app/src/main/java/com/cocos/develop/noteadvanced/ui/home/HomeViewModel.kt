package com.cocos.develop.noteadvanced.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cocos.develop.noteadvanced.data.NoteData
import com.cocos.develop.noteadvanced.data.domain.LocalRepository
import com.cocos.develop.noteadvanced.rx.SchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.java.KoinJavaComponent

class HomeViewModel : ViewModel() {

    private val _homeLiveData = MutableLiveData<List<NoteData>>()
    private val homeLiveData: LiveData<List<NoteData>> = _homeLiveData

    private var currentDisposable = CompositeDisposable()
    private val schedulerProvider: SchedulerProvider = SchedulerProvider()
    private val usersRepoImpl : LocalRepository by KoinJavaComponent.inject(LocalRepository::class.java)

    fun subscribe(): LiveData<List<NoteData>> {
        return homeLiveData
    }

    fun getData() {
        currentDisposable.add(
            usersRepoImpl.getNotes()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                    { noteList -> _homeLiveData.postValue(noteList) },
                    { error -> Log.e("Note list loader", error.message.toString()) })

        )
    }

    override fun onCleared() {
        currentDisposable.clear()
        super.onCleared()
    }
}