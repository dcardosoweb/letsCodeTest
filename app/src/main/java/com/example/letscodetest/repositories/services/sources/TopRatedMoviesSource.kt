package com.example.letscodetest.repositories.services.sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.letscodetest.repositories.db.IFavoriteMovieDB
import com.example.letscodetest.repositories.db.MovieEntity
import com.example.letscodetest.repositories.services.BaseApi
import com.example.letscodetest.repositories.services.ITheMovieDBService
import com.example.letscodetest.repositories.services.responses.asEntity
import com.example.letscodetest.utils.ResponseNoBodyException

class TopRatedMoviesSource (private val theMovieDBService: ITheMovieDBService, private val favoriteMovieDB: IFavoriteMovieDB, val userId:String) : PagingSource<Int, MovieEntity>() {

    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? =
        state.anchorPosition?.let { state.closestItemToPosition(it)?.id?.toInt() }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
        return try {
            val pageNumber = params.key ?: 1
            val response = theMovieDBService.getTopRatedMovies(
                        BaseApi.theMovieDBKey,
                        "pt-BR",
                        pageNumber.toString()
                    )

            val movieList = response?.body()?.results?.asEntity()


            movieList?.let {

                for (item in it){
                    item.favorite = favoriteMovieDB.checkFavoriteUserMovie(userId,item.movieId)!=null
                }

                LoadResult.Page(
                    it,
                    null,
                    pageNumber+1
                )
            } ?: throw ResponseNoBodyException()
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }
}