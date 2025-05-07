package com.example.movieapp.data.remote.datasource

import com.example.movieapp.data.remote.api.MovieResponse
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getMoviesList(category: String, page: Int): Response<MovieResponse>
}
