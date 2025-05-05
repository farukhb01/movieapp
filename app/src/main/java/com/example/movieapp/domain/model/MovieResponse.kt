package com.example.movieapp.domain.model

import com.example.movieapp.data.local.entity.MovieEntity

data class MovieResponse(
    val page: Int,
    val results: List<MovieEntity>,
    val total_pages: Int,
    val total_results: Int
)