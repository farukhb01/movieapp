package com.example.movieapp.ui.fragments.favmovies

import com.example.movieapp.data.local.db.entity.MovieEntity

sealed class FavoriteMovieIntent {
    data object LoadFavorites : FavoriteMovieIntent()
    data class AddFavorite(val movie: MovieEntity) : FavoriteMovieIntent()
    data class RemoveFavorite(val movieId: Int) : FavoriteMovieIntent()
}