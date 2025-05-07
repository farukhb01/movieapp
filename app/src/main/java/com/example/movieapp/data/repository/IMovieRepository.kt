package com.example.movieapp.data.repository

import androidx.paging.PagingData
import com.example.movieapp.data.local.db.entity.MovieEntity
import com.example.movieapp.data.remote.api.MovieDto
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getPagedMovies(category: String): Flow<PagingData<MovieDto>>
    fun getFavoriteMovies(): Flow<List<MovieEntity>>
    suspend fun insertMovie(movie: MovieEntity)
    suspend fun deleteMovieById(id: Int)
}