package com.example.movieapp.data.remote.api

import com.example.movieapp.core.constants.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/{category}")
    suspend fun getMoviesList(
        @Path("category") category: String,
        @Query("language") language: String = "en",
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MovieResponse>
}
