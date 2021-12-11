package com.cocos.develop.noteadvanced.data.di

import androidx.room.Room
import com.cocos.develop.noteadvanced.data.datasource.NoteApi
import com.cocos.develop.noteadvanced.data.domain.LocalRepoImpl
import com.cocos.develop.noteadvanced.data.domain.LocalRepository
import com.cocos.develop.noteadvanced.data.domain.RemoteRepoImpl
import com.cocos.develop.noteadvanced.data.domain.RemoteRepository
import com.cocos.develop.noteadvanced.data.room.NoteDataBase
import com.cocos.develop.noteadvanced.ui.dashboard.DashboardViewModel
import com.cocos.develop.noteadvanced.ui.details.DetailViewModel
import com.cocos.develop.noteadvanced.ui.home.HomeViewModel
import com.cocos.develop.noteadvanced.ui.favorite.FavoriteViewModel
import com.cocos.develop.noteadvanced.ui.start.StartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * homework com.cocos.develop.noteadvanced.data.di
 *
 * @author Amina
 * 24.11.2021
 */
const val DB_NAME = "noteDB"
const val BASE_URL = "http://194.58.108.107"

val application = module {
    single { Room.databaseBuilder(get(), NoteDataBase::class.java, DB_NAME).build() }
}

val apiModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NoteApi::class.java)
    }
}

val repoModule = module {
//    single<GithubUsersRepo> {
//        GithubUserRepoCombinedImpl(
//            GithubUsersLocalRepoImpl(get()),
//            GithubUsersWebRepoImpl(get()),
//            NetworkStatusImpl(get()),
//            SchedulerProvider()
//        )
//    }
    single<RemoteRepository>{
        RemoteRepoImpl(get())
    }
    single<LocalRepository>{
        LocalRepoImpl(get())
    }
}

val homeScreen = module {
    viewModel { HomeViewModel() }
}

val startScreen = module {
    viewModel { StartViewModel() }
}

val detailScreen = module {
    viewModel { DetailViewModel() }
}

val dashboardScreen = module {
    viewModel { DashboardViewModel() }
}

val favoriteScreen = module {
    viewModel { FavoriteViewModel() }
}