package com.example.movieapp.domain.usecases.getmoviesusecase

import androidx.paging.PagingData
import com.example.movieapp.data.local.db.entity.MovieEntity
import com.example.movieapp.data.remote.api.MovieDto
import com.example.movieapp.data.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class GetPagedMoviesUseCase(
    private val repository: IMovieRepository
) : IGetPagedMoviesUseCase {
    override operator fun invoke(category: String): Flow<PagingData<MovieDto>> {
        return repository.getPagedMovies(category)
    }
}
