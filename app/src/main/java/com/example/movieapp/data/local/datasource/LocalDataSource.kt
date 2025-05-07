package com.example.movieapp.data.local.datasource

import com.example.movieapp.data.local.db.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getAllMovies(): Flow<List<MovieEntity>>
    suspend fun insertMovie(movie: MovieEntity)
    suspend fun deleteMovieById(id: Int)
}