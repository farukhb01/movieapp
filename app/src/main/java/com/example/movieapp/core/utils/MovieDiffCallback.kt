package com.example.movieapp.core.utils

import com.example.movieapp.core.base.adapter.BaseDiffUtils
import com.example.movieapp.data.local.db.entity.MovieEntity
import com.example.movieapp.data.remote.api.MovieDto

class MovieDiffCallback : BaseDiffUtils<MovieDto>() {
    override fun areItemsTheSame(oldItem: MovieDto, newItem: MovieDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieDto, newItem: MovieDto): Boolean {
        return oldItem == newItem
    }
}
