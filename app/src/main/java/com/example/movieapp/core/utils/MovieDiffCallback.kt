package com.example.movieapp.core.utils

import com.example.movieapp.core.base.adapter.BaseDiffUtils
import com.example.movieapp.data.local.entity.MovieEntity

class MovieDiffCallback : BaseDiffUtils<MovieEntity>() {
    override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
        return oldItem == newItem
    }
}
