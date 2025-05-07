package com.example.movieapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.domain.usecases.deletemovieusecase.IDeleteMovieUseCase
import com.example.movieapp.domain.usecases.getfavmoviesusecase.IGetFavoriteMoviesUseCase
import com.example.movieapp.domain.usecases.getmoviesusecase.IGetPagedMoviesUseCase
import com.example.movieapp.domain.usecases.insertmovieusecase.IInsertMovieUseCase

class MovieViewModelFactory(
    private val getPagedMoviesUseCase: IGetPagedMoviesUseCase,
    private val getFavoriteMoviesUseCase: IGetFavoriteMoviesUseCase,
    private val insertMovieUseCase: IInsertMovieUseCase,
    private val deleteMovieUseCase: IDeleteMovieUseCase
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
