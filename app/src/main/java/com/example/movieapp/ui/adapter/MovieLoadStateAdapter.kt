package com.example.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R

class MovieLoadStateAdapter : LoadStateAdapter<LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_load_state, parent, false)
        return LoadStateViewHolder(view)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}


class LoadStateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)

    fun bind(loadState: LoadState) {
        progressBar.isVisible = loadState is LoadState.Loading
    }
}
