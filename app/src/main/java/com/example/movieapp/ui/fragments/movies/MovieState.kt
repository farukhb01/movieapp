package com.example.movieapp.ui.fragments.movies

import androidx.paging.PagingData
import com.example.movieapp.data.remote.api.MovieDto

sealed class MovieState {
    data class MoviesLoaded(val pagingData: PagingData<MovieDto>) : MovieState()
}