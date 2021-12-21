package com.cocos.develop.noteadvanced.ui.start

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cocos.develop.noteadvanced.data.Token
import com.cocos.develop.noteadvanced.data.User
import com.cocos.develop.noteadvanced.data.domain.AppState
import com.cocos.develop.noteadvanced.data.domain.RemoteRepository
import com.cocos.develop.noteadvanced.rx.SchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.java.KoinJavaComponent

/**
 * homework com.cocos.develop.noteadvanced.ui.start
 *
 * @author Amina
 * 30.11.2021
 */
class StartViewModel: ViewModel() {

    private val _startLiveData = MutableLiveData<AppState>()
    private val startLiveData: LiveData<AppState> = _startLiveData

    private var currentDisposable = CompositeDisposable()
    private val schedulerProvider: SchedulerProvider = SchedulerProvider()
    private val usersRepoImpl : RemoteRepository by KoinJavaComponent.inject(RemoteRepository::class.java)

    fun subscribe(): LiveData<AppState> {
        return startLiveData
    }

    fun getData(user:User) {
        _startLiveData.postValue(AppState.Loading(null))
        currentDisposable.add(
            usersRepoImpl.getToken(user)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                    { token -> _startLiveData.postValue(AppState.Success<Token>(token))},
                    { error ->
                        _startLiveData.postValue(AppState.Error(error))
                        Log.e("Error authorization", error.message.toString())
                    })

        )
    }

    override fun onCleared() {
        currentDisposable.clear()
        super.onCleared()
    }

}