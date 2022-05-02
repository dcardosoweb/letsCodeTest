package com.example.letscodetest.utils

import com.example.letscodetest.repositories.db.MovieEntity

interface OnClickMovieAdapterListener {
    fun setOnClickFavoriteListener(movie: MovieEntity)
    fun setOnClickUnfavoriteListener(Id: Long)
}