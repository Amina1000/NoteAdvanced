package com.cocos.develop.noteadvanced.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cocos.develop.noteadvanced.data.NoteData
import com.cocos.develop.noteadvanced.data.domain.LocalRepository
import com.cocos.develop.noteadvanced.rx.SchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.java.KoinJavaComponent

/**
 * homework com.cocos.develop.noteadvanced.ui.details
 *
 * @author Amina
 * 22.11.2021
 */
class DetailViewModel: ViewModel() {

    private val _detailsLiveData = MutableLiveData<NoteData>()
    private val detailsLiveData: LiveData<NoteData> = _detailsLiveData

    private var currentDisposable = CompositeDisposable()
    private val schedulerProvider: SchedulerProvider = SchedulerProvider()
    private val usersRepoImpl : LocalRepository by KoinJavaComponent.inject(LocalRepository::class.java)

    fun subscribe(): LiveData<NoteData> {
        return detailsLiveData
    }

    fun setData(data: NoteData) {
        currentDisposable.add(
            usersRepoImpl.putNote(data)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                    { Log.i("Note Data Save", "save note data success") },
                    { error -> Log.e("Note Data Save", error.message.toString()) })

        )
    }

    override fun onCleared() {
        super.onCleared()
        currentDisposable.clear()
    }
}