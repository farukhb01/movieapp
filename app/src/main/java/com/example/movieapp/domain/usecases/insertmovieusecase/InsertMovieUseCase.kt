package com.example.movieapp.domain.usecases.insertmovieusecase

import com.example.movieapp.data.local.db.entity.MovieEntity
import com.example.movieapp.data.repository.IMovieRepository

class InsertMovieUseCase(
    private val repository: IMovieRepository
) : IInsertMovieUseCase {
    override suspend operator fun invoke(movie: MovieEntity) {
        repository.insertMovie(movie)
    }
}
