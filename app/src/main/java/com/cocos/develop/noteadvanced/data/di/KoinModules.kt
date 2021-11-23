package com.cocos.develop.noteadvanced.data.di

import androidx.room.Room
import com.cocos.develop.noteadvanced.data.domain.LocalRepoImpl
import com.cocos.develop.noteadvanced.data.domain.LocalRepository
import com.cocos.develop.noteadvanced.data.room.NoteDataBase
import com.cocos.develop.noteadvanced.ui.details.DetailViewModel
import com.cocos.develop.noteadvanced.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * homework com.cocos.develop.noteadvanced.data.di
 *
 * @author Amina
 * 24.11.2021
 */
const val DB_NAME = "noteDB"
//const val BASE_URL = "https://api.github.com"

val application = module {
    single { Room.databaseBuilder(get(), NoteDataBase::class.java, DB_NAME).build() }
}

//val apiModule = module {
//    single { Moshi.Builder().add(KotlinJsonAdapterFactory()).build() }
//    single { OkHttpClient.Builder().addNetworkInterceptor(StethoInterceptor()).build() }
//    single {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(get())
//            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//            .addConverterFactory(MoshiConverterFactory.create(get()))
//            .build()
//            .create(GitHubApi::class.java)
//    }
//}

val repoModule = module {
//    single<GithubUsersRepo> {
//        GithubUserRepoCombinedImpl(
//            GithubUsersLocalRepoImpl(get()),
//            GithubUsersWebRepoImpl(get()),
//            NetworkStatusImpl(get()),
//            SchedulerProvider()
//        )
//    }
    single<LocalRepository>{
        LocalRepoImpl(get())
    }
}

val homeScreen = module {
    viewModel { HomeViewModel() }
}

val detailScreen = module {
    viewModel { DetailViewModel() }
}