package com.example.movieapp.presentation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.core.base.adapter.BasePagingAdapter
import com.example.movieapp.core.utils.MovieDiffCallback
import com.example.movieapp.data.local.entity.MovieEntity
import com.example.movieapp.service.DownloadService
import com.squareup.picasso.Picasso

class MovieAdapter(
    private val isFavoriteScreen: Boolean,
    private val onIconClick: (MovieEntity) -> Unit
) : BasePagingAdapter<MovieEntity, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgPoster: ImageView = view.findViewById(R.id.imgPoster)
        val movieTitle: TextView = view.findViewById(R.id.movieTitle)
        val tvRating: TextView = view.findViewById(R.id.tvRating)
        val tvOverview: TextView = view.findViewById(R.id.tvOverview)
        val tvLike: ImageView = view.findViewById(R.id.tvLike)
        val tvDownload: ImageView = view.findViewById(R.id.tvDownload)
    }

    override fun getViewHolder(view: View): MovieViewHolder {
        return MovieViewHolder(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return getViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position) !!

        val imageUrl = "https://image.tmdb.org/t/p/w500${movie.poster_path}"
        Picasso.get().load(imageUrl).into(holder.imgPoster)

        holder.movieTitle.text = movie.title
        holder.tvOverview.text = movie.overview
        holder.tvRating.text = "Rating: ${movie.vote_average}"

        holder.tvLike.setImageResource(
            if (isFavoriteScreen) R.drawable.ic_delete else R.drawable.ic_favorite
        )

        holder.tvLike.setOnClickListener { onIconClick(movie) }

        holder.tvDownload.setOnClickListener {
            val context = holder.itemView.context
            Toast.makeText(context, "Downloading...", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, DownloadService::class.java).apply {
                putExtra("movie_name", movie.title)
            }
            ContextCompat.startForegroundService(context, intent)
        }
    }

}

