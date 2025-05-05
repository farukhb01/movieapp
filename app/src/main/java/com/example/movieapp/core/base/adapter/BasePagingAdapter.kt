package com.example.movieapp.core.base.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.paging.PagingDataAdapter

abstract class BasePagingAdapter<T : Any, VH : RecyclerView.ViewHolder>(
    diffCallback: DiffUtil.ItemCallback<T>
) : PagingDataAdapter<T, VH>(diffCallback) {

    abstract fun getViewHolder(view: View): VH

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH
}