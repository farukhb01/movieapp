package com.example.movieapp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.data.local.dao.MovieDao
import com.example.movieapp.data.local.entity.MovieEntity
import com.example.movieapp.data.remote.api.MovieApiService
import com.example.movieapp.paging.MoviePagingSource
import kotlinx.coroutines.flow.Flow


class MovieRepository(
    private val movieDao: MovieDao,
    private val api: MovieApiService
) {

    fun getPagedMovies(category: String): Flow<PagingData<MovieEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 20, maxSize = 100),
            pagingSourceFactory = { MoviePagingSource(api, category) }
        ).flow
    }

    fun getFavoriteMovies(): LiveData<List<MovieEntity>> {
       return movieDao.getAllMovies()
    }

    suspend fun insertMovie(movie: MovieEntity) {
        movieDao.insertMovie(movie)
    }

    suspend fun deleteMovieById(id: Int){
        movieDao.deleteMovieById(id)
    }
}
