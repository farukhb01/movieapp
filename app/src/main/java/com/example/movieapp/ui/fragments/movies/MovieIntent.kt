package com.example.movieapp.ui.fragments.movies


sealed class MovieIntent {
    data class LoadMovies(val category: String) : MovieIntent()
}