package com.example.letscodetest.di

import com.example.letscodetest.repositories.TheAudioDBRepository
import com.example.letscodetest.repositories.TheMovieDBRepository
import com.example.letscodetest.repositories.db.DataBaseApplication
import com.example.letscodetest.repositories.services.BaseApi
import com.example.letscodetest.repositories.services.ITheAudioDBService
import com.example.letscodetest.repositories.services.ITheMovieDBService
import com.example.letscodetest.viewmodels.MusicViewModel
import com.example.letscodetest.viewmodels.RegisterUserViewModel
import com.example.letscodetest.viewmodels.TopRatedMoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

val viewModule = module {
    viewModel {
        RegisterUserViewModel(
        )
    }

    viewModel {
        TopRatedMoviesViewModel(
            movieRepository = get()
        )
    }

    viewModel {
        MusicViewModel(
            audioRepository = get()
        )
    }
}

val repositoryModule = module {
    single {
        TheMovieDBRepository(movieService = get(), favoriteMovieDB = get())
    }
    single {
        TheAudioDBRepository(musicService = get(), favoriteMusicDB = get())
    }
}

val apiModule = module {
    single {
        BaseApi.getMovieRetrofitInstance(context = get()).create(ITheMovieDBService::class.java)
    }
    single {
        BaseApi.getMusicRetrofitInstance(context = get()).create(ITheAudioDBService::class.java)
    }
}

val dbModule = module {
    single { DataBaseApplication.getInstance(context = get()) }
    single { get<DataBaseApplication>().favoriteMovieDB() }
    single { get<DataBaseApplication>().favoriteMusicDB() }
}