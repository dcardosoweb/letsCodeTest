package com.example.letscodetest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.letscodetest.repositories.TheMovieDBRepository
import com.example.letscodetest.repositories.db.MovieEntity
import kotlinx.coroutines.launch

class TopRatedMoviesViewModel(
    private val movieRepository: TheMovieDBRepository
): ViewModel() {

    fun getMoviesFromServer() = movieRepository
        .getMoviesFromServer()
        .cachedIn(viewModelScope)

    fun favoriteMovie(
        movie : MovieEntity
    ) = viewModelScope.launch {
        movieRepository.favoriteMovie(movie)
    }

    fun unfavoriteMovie(
        id : Long
    ) = viewModelScope.launch {
        movieRepository.unfavoriteMovie(id)
    }
}