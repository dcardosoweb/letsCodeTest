package com.example.letscodetest.repositories.services

import com.example.letscodetest.repositories.services.responses.GetMusicResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ITheAudioDBService {

    @Headers("Content-Type: application/json")
    @GET("json/2/mvid.php")
    suspend fun getMusic(@Query("i") id: String): Response<GetMusicResponse>
}