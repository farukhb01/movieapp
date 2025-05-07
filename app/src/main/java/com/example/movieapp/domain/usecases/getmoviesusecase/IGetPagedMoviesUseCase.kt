package com.example.movieapp.domain.usecases.getmoviesusecase

import androidx.paging.PagingData
import com.example.movieapp.data.local.db.entity.MovieEntity
import com.example.movieapp.data.remote.api.MovieDto
import kotlinx.coroutines.flow.Flow

interface IGetPagedMoviesUseCase {
    operator fun invoke(category: String): Flow<PagingData<MovieDto>>
}
