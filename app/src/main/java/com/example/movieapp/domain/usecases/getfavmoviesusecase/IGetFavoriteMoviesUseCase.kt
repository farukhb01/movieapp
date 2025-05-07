package com.example.movieapp.domain.usecases.getfavmoviesusecase

import com.example.movieapp.data.local.db.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface IGetFavoriteMoviesUseCase {
    operator fun invoke(): Flow<List<MovieEntity>>
}