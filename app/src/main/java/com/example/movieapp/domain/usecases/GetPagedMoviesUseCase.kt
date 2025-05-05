package com.example.movieapp.domain.usecases

import androidx.paging.PagingData
import com.example.movieapp.data.local.entity.MovieEntity
import com.example.movieapp.data.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetPagedMoviesUseCase(
    private val repository: MovieRepository
) {
    operator fun invoke(category: String): Flow<PagingData<MovieEntity>> {
        return repository.getPagedMovies(category)
    }
}