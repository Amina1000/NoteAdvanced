package com.cocos.develop.noteadvanced.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cocos.develop.noteadvanced.data.User
import com.cocos.develop.noteadvanced.data.domain.AppState
import com.cocos.develop.noteadvanced.data.domain.LocalRepository
import com.cocos.develop.noteadvanced.data.domain.RemoteRepository
import com.cocos.develop.noteadvanced.rx.SchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.java.KoinJavaComponent

class DashboardViewModel : ViewModel() {

    private val _dashboardLiveData = MutableLiveData<AppState>()
    private val dashboardLiveData: LiveData<AppState> = _dashboardLiveData

    private var currentDisposable = CompositeDisposable()
    private val schedulerProvider: SchedulerProvider = SchedulerProvider()
    private val usersRepoLocalImpl: LocalRepository by KoinJavaComponent.inject(LocalRepository::class.java)
    private val usersRepoRemoteImpl: RemoteRepository by KoinJavaComponent.inject(RemoteRepository::class.java)

    fun subscribe(): LiveData<AppState> {
        return dashboardLiveData
    }

    fun getLocalData() {
        currentDisposable.add(
            usersRepoLocalImpl.getUsers()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                    { userList ->
                        if (!userList.isNullOrEmpty()) {
                            _dashboardLiveData.postValue(AppState.Success(userList.last()))
                        }
                    },
                    { error ->
                        AppState.Error(error)
                        Log.e("User list loader", error.message.toString())
                    })
        )
    }

    fun getData(access: String?) {
        if (access != null) {
            currentDisposable.add(
                usersRepoRemoteImpl.getUserId(access)
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribe(
                        { userList ->
                            if (!userList.isNullOrEmpty()) {
                                usersRepoRemoteImpl.getUser(userList.first().id, access)
                                    .subscribeOn(schedulerProvider.io())
                                    .observeOn(schedulerProvider.ui())
                                    .subscribe(
                                        { user ->
                                            user.id = userList.first().id
                                            _dashboardLiveData.postValue(AppState.Success(user))
                                        },
                                        { error ->
                                            AppState.Error(error)
                                            Log.e("User list loader", error.message.toString())
                                        })
                            }
                        },
                        { error ->
                            AppState.Error(error)
                            Log.e("User list loader", error.message.toString())
                        })
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
                        userData.email = user.email
                        _dashboardLiveData.postValue(AppState.Success(userData))
                        user.id = userData.id
                        putLocalData(user)
                        Log.i("User Data Save", "save user data success")
                    },
                    { error ->
                        AppState.Error(error)
                        Log.e("User Data Save", error.message.toString())
                    })

        )
    }

    private fun putLocalData(user: User) =
        usersRepoLocalImpl.putUser(user)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe(
                { Log.i("User data", "save note data success") },
                { error -> Log.e("Usaer data", error.message.toString()) })


    override fun onCleared() {
        currentDisposable.clear()
        super.onCleared()
    }
}