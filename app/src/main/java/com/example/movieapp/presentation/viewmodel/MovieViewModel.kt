package com.example.movieapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.core.base.viewmodel.BaseViewModel
import com.example.movieapp.data.local.entity.MovieEntity
import com.example.movieapp.domain.usecases.DeleteMovieUseCase
import com.example.movieapp.domain.usecases.GetFavoriteMoviesUseCase
import com.example.movieapp.domain.usecases.GetPagedMoviesUseCase
import com.example.movieapp.domain.usecases.InsertMovieUseCase

class MovieViewModel(
    private val getPagedMoviesUseCase: GetPagedMoviesUseCase,
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val insertMovieUseCase: InsertMovieUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase
) :  BaseViewModel() {

    private val _pagedMovies = MutableLiveData<PagingData<MovieEntity>>(PagingData.empty())
    val pagedMovies: LiveData<PagingData<MovieEntity>> get() = _pagedMovies
    val favoriteMovies: LiveData<List<MovieEntity>> = getFavoriteMoviesUseCase()

    fun fetchPagedMovies(category: String) {
        launchCoroutine {
            getPagedMoviesUseCase(category)
                .cachedIn(viewModelScope)
                .collect { pagingData ->
                    _pagedMovies.postValue(pagingData)
                }
        }
    }
    fun insertMovie(movie: MovieEntity) {
        launchCoroutine  {
            insertMovieUseCase(movie)
        }
    }

    fun deleteMovieById(id: Int) {
        launchCoroutine  {
            deleteMovieUseCase(id)
        }
    }
}
