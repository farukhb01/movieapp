package com.example.movieapp.data.local.datasource

import com.example.movieapp.data.local.db.dao.MovieDao
import com.example.movieapp.data.local.db.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(private val movieDao: MovieDao) : LocalDataSource {

    override fun getAllMovies(): Flow<List<MovieEntity>> {
        return movieDao.getAllMovies()
    }

    override suspend fun insertMovie(movie: MovieEntity) {
        movieDao.insertMovie(movie)
    }

    override suspend fun deleteMovieById(id: Int) {
        movieDao.deleteMovieById(id)
    }
}
