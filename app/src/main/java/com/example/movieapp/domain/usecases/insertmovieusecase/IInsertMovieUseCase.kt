package com.example.movieapp.domain.usecases.insertmovieusecase

import com.example.movieapp.data.local.db.entity.MovieEntity

interface IInsertMovieUseCase {
    suspend operator fun invoke(movie: MovieEntity)
}
