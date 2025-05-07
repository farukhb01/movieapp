package com.example.movieapp.data.remote.api

import com.example.movieapp.data.local.db.entity.MovieEntity

data class MovieResponse(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)