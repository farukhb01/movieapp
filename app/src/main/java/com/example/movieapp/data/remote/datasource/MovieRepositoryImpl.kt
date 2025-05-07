package com.example.movieapp.data.remote.datasource

import com.example.movieapp.data.remote.api.MovieApiService
import com.example.movieapp.data.remote.api.MovieResponse
import retrofit2.Response

class RemoteDataSourceImpl(
    private val movieApiService: MovieApiService
) : RemoteDataSource {

    override suspend fun getMoviesList(category: String, page: Int): Response<MovieResponse> {
        return movieApiService.getMoviesList(
            category = category,
            page = page
        )
    }
}