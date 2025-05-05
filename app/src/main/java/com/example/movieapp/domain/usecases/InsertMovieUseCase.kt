package com.example.movieapp.domain.usecases

import com.example.movieapp.data.local.entity.MovieEntity
import com.example.movieapp.data.repository.MovieRepository

class InsertMovieUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movie: MovieEntity) {
        repository.insertMovie(movie)
    }
}