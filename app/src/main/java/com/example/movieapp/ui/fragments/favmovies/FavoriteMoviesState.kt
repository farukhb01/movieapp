package com.example.movieapp.ui.fragments.favmovies

import com.example.movieapp.data.local.db.entity.MovieEntity

sealed class FavoriteMovieState {
    data class FavoritesLoaded(val movies: List<MovieEntity>) : FavoriteMovieState()
    data class FavoriteUpdated(val message: String) : FavoriteMovieState()
}
