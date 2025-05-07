package com.example.movieapp.ui.viewmodel


import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.movieapp.core.base.viewmodel.BaseViewModel
import com.example.movieapp.data.local.db.entity.MovieEntity
import com.example.movieapp.domain.usecases.deletemovieusecase.IDeleteMovieUseCase
import com.example.movieapp.domain.usecases.getfavmoviesusecase.IGetFavoriteMoviesUseCase
import com.example.movieapp.domain.usecases.getmoviesusecase.IGetPagedMoviesUseCase
import com.example.movieapp.domain.usecases.insertmovieusecase.IInsertMovieUseCase
import com.example.movieapp.ui.fragments.favmovies.FavoriteMovieIntent
import com.example.movieapp.ui.fragments.favmovies.FavoriteMovieState
import com.example.movieapp.ui.fragments.movies.MovieIntent
import com.example.movieapp.ui.fragments.movies.MovieState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
class MovieViewModel(
    private val getPagedMoviesUseCase: IGetPagedMoviesUseCase,
    private val getFavoriteMoviesUseCase: IGetFavoriteMoviesUseCase,
    private val insertMovieUseCase: IInsertMovieUseCase,
    private val deleteMovieUseCase: IDeleteMovieUseCase
) : BaseViewModel() {

    private val _movieState = MutableStateFlow<MovieState?>(null)
    val movieState: StateFlow<MovieState?> = _movieState

    private val _favoriteState = MutableStateFlow<FavoriteMovieState?>(null)
    val favoriteState: StateFlow<FavoriteMovieState?> = _favoriteState

    fun processIntent(intent: MovieIntent) {
        when (intent) {
            is MovieIntent.LoadMovies -> loadMovies(intent.category)
        }
    }

    fun processFavoriteIntent(intent: FavoriteMovieIntent) {
        when (intent) {
            is FavoriteMovieIntent.LoadFavorites -> loadFavorites()
            is FavoriteMovieIntent.AddFavorite -> addFavorite(intent.movie)
            is FavoriteMovieIntent.RemoveFavorite -> removeFavorite(intent.movieId)
        }
    }

    private fun loadMovies(category: String) {
        launchCoroutine {
            getPagedMoviesUseCase(category)
                .cachedIn(viewModelScope)
                .collect { pagingData ->
                    _movieState.value = MovieState.MoviesLoaded(pagingData)
                }
        }
    }

    private fun loadFavorites() {
        launchCoroutine {
            getFavoriteMoviesUseCase().collect { favorites ->
                _favoriteState.value = FavoriteMovieState.FavoritesLoaded(favorites)
            }
        }
    }

    private fun addFavorite(movie: MovieEntity) {
        launchCoroutine {
            insertMovieUseCase(movie)
        }
    }

    private fun removeFavorite(movieId: Int) {
        launchCoroutine {
            deleteMovieUseCase(movieId)
        }
    }
}