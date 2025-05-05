package com.example.movieapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.domain.usecases.DeleteMovieUseCase
import com.example.movieapp.domain.usecases.GetFavoriteMoviesUseCase
import com.example.movieapp.domain.usecases.GetPagedMoviesUseCase
import com.example.movieapp.domain.usecases.InsertMovieUseCase

class MovieViewModelFactory(
    private val getPagedMoviesUseCase: GetPagedMoviesUseCase,
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val insertMovieUseCase: InsertMovieUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MovieViewModel(
                getPagedMoviesUseCase,
                getFavoriteMoviesUseCase,
                insertMovieUseCase,
                deleteMovieUseCase
            ) as T

    }
}
