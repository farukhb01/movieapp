package com.example.movieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.data.local.datasource.LocalDataSource
import com.example.movieapp.data.local.db.entity.MovieEntity
import com.example.movieapp.data.remote.api.MovieDto
import com.example.movieapp.data.remote.datasource.RemoteDataSource
import com.example.movieapp.paging.MoviePagingSource
import kotlinx.coroutines.flow.Flow


class MovieRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
): IMovieRepository {

    override fun getPagedMovies(category: String): Flow<PagingData<MovieDto>> {
        return Pager(
            config = PagingConfig(pageSize = 20, maxSize = 100),
            pagingSourceFactory = { MoviePagingSource(remoteDataSource, category) }
        ).flow
    }

    override fun getFavoriteMovies(): Flow<List<MovieEntity>> {
       return localDataSource.getAllMovies()
    }

    override suspend fun insertMovie(movie: MovieEntity) {
        localDataSource.insertMovie(movie)
    }

    override suspend fun deleteMovieById(id: Int){
        localDataSource.deleteMovieById(id)
    }
}
