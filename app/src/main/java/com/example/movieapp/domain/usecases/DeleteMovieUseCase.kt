package com.example.movieapp.domain.usecases

import com.example.movieapp.data.repository.MovieRepository

class DeleteMovieUseCase(
    private val repository: MovieRepository
) {
        suspend operator fun invoke(id: Int) {
            repository.deleteMovieById(id)
        }

}