package com.example.movieapp.domain.usecases.deletemovieusecase

import com.example.movieapp.data.repository.IMovieRepository

class DeleteMovieUseCase(
    private val repository: IMovieRepository
) : IDeleteMovieUseCase {
    override suspend operator fun invoke(id: Int) {
        repository.deleteMovieById(id)
    }
}