package com.example.movieapp.domain.usecases.deletemovieusecase

interface IDeleteMovieUseCase {
    suspend operator fun invoke(id: Int)
}
