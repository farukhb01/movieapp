package com.example.movieapp.domain.usecases

import androidx.lifecycle.LiveData
import com.example.movieapp.data.local.entity.MovieEntity
import com.example.movieapp.data.repository.MovieRepository

class GetFavoriteMoviesUseCase(
    private val repository: MovieRepository
) {
    operator fun invoke(): LiveData<List<MovieEntity>> {
        return repository.getFavoriteMovies()
    }
}