package com.example.letscodetest.repositories.services

import com.example.letscodetest.repositories.services.responses.GetTopRatedMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ITheMovieDBService {

    @Headers("Content-Type: application/json")
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String,@Query("language") language: String,@Query("page") page: String):Response<GetTopRatedMoviesResponse>
}