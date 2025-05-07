package com.example.movieapp.domain.usecases.getfavmoviesusecase

import com.example.movieapp.data.local.db.entity.MovieEntity
import com.example.movieapp.data.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteMoviesUseCase(
    private val repository: IMovieRepository
) : IGetFavoriteMoviesUseCase {
    override operator fun invoke(): Flow<List<MovieEntity>> {
        return repository.getFavoriteMovies()
    }
}