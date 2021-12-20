package com.cocos.develop.noteadvanced.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cocos.develop.noteadvanced.data.User
import com.cocos.develop.noteadvanced.data.domain.LocalRepository
import com.cocos.develop.noteadvanced.data.domain.RemoteRepository
import com.cocos.develop.noteadvanced.rx.SchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.java.KoinJavaComponent

class DashboardViewModel : ViewModel() {

    private val _dashboardLiveData = MutableLiveData<List<User>>()
    private val dashboardLiveData: LiveData<List<User>> = _dashboardLiveData

    private var currentDisposable = CompositeDisposable()
    private val schedulerProvider: SchedulerProvider = SchedulerProvider()
    private val usersRepoLocalImpl: LocalRepository by KoinJavaComponent.inject(LocalRepository::class.java)
    private val usersRepoRemoteImpl: RemoteRepository by KoinJavaComponent.inject(RemoteRepository::class.java)

    fun subscribe(): LiveData<List<User>> {
        return dashboardLiveData
    }

    fun getData(access: String?) {
        if (access != null) {
            currentDisposable.add(
                usersRepoLocalImpl.getUsers()
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribe(
                        { userList -> _dashboardLiveData.postValue(userList) },
                        { error -> Log.e("User list loader", error.message.toString()) })
            )
        }
    }

    fun setData(access: String?, user: User) {

        currentDisposable.add(
            usersRepoRemoteImpl.putUser(access, user)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                    { userData ->
                        _dashboardLiveData.postValue(listOf(userData))
                        user.id = userData.id
                        puLocalData(user)
                        Log.i("User Data Save", "save user data success")
                    },
                    { error -> Log.e("User Data Save", error.message.toString()) })

        )
    }

    private fun puLocalData(user: User) =
        usersRepoLocalImpl.putUser(user)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe(
                { Log.i("Note Data Save", "save note data success") },
                { error -> Log.e("Note Data Save", error.message.toString()) })


    override fun onCleared() {
        currentDisposable.clear()
        super.onCleared()
    }
}