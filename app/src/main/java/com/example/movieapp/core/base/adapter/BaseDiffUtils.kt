package com.example.movieapp.core.base.adapter

import androidx.recyclerview.widget.DiffUtil

open class BaseDiffUtils<Model: Any>: DiffUtil.ItemCallback<Model>() {
    override fun areItemsTheSame(oldItem: Model, newItem: Model): Boolean = false

    override fun areContentsTheSame(oldItem: Model, newItem: Model): Boolean = false

}

