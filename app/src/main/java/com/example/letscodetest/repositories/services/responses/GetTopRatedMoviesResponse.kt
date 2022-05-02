package com.example.letscodetest.repositories.services.responses

import com.example.letscodetest.repositories.db.MovieEntity
import com.google.gson.annotations.SerializedName

class GetTopRatedMoviesResponse(
    val page: Long,
    val results: List<MovieData>,

    @SerializedName("total_pages")
    val totalPages: Long,

    @SerializedName( "total_results")
    val totalResults: Long

    )
{

    data class MovieData (
        val adult: Boolean,
        @SerializedName( "backdrop_path")
        val backdropPath: String,
        @SerializedName( "genre_ids")
        val genreIDS: List<Long>,
        val id: Long,
        @SerializedName( "original_language")
        val originalLanguage: String,
        @SerializedName("original_title")
        val originalTitle: String,
        val overview: String,
        val popularity: Double,
        @SerializedName( "poster_path")
        val posterPath: String,
        @SerializedName( "release_date")
        val releaseDate: String,
        val title: String,
        val video: Boolean,
        @SerializedName( "vote_average")
        val voteAverage: Double,
        @SerializedName( "vote_count")
        val voteCount: Long
    )
}

fun GetTopRatedMoviesResponse.MovieData.asEntity(): MovieEntity {
    return MovieEntity( id, posterPath)
}

fun List<GetTopRatedMoviesResponse.MovieData>.asEntity(): List<MovieEntity> {
    return map {
        it.asEntity()
    }
}