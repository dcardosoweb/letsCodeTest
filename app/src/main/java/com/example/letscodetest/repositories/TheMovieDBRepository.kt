package com.example.letscodetest.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.letscodetest.repositories.db.MovieEntity
import com.example.letscodetest.repositories.db.IFavoriteMovieDB
import com.example.letscodetest.repositories.services.ITheMovieDBService
import com.example.letscodetest.repositories.services.sources.TopRatedMoviesSource
import com.example.letscodetest.utils.SessionUser

class TheMovieDBRepository( val movieService: ITheMovieDBService, val favoriteMovieDB: IFavoriteMovieDB) {

    fun getMoviesFromServer() = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
            enablePlaceholders = false,
        ),
        pagingSourceFactory = {
            TopRatedMoviesSource(
                movieService,favoriteMovieDB, SessionUser.userId!!)
        }
    ).liveData

    fun favoriteMovie(movieEntity: MovieEntity){
        movieEntity.userId = SessionUser.userId
        favoriteMovieDB.insert(movieEntity)
    }

    fun unfavoriteMovie(id:Long){
        favoriteMovieDB.unfavoriteMovie(id)
    }

}