package com.example.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.core.base.adapter.BasePagingAdapter
import com.example.movieapp.core.utils.MovieDiffCallback
import com.example.movieapp.data.local.db.entity.MovieEntity
import com.example.movieapp.data.remote.api.MovieDto
import com.example.movieapp.domain.mapper.DomainToEntityMapper
import com.example.movieapp.data.mapper.DtoToDomainMapper
import com.squareup.picasso.Picasso

class MovieAdapter(
    private val isFavoriteScreen: Boolean,
    private val onIconClick: (MovieEntity) -> Unit
) : BasePagingAdapter<MovieDto, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {
    val dtoToDomain = DtoToDomainMapper()
    val domainToEntity = DomainToEntityMapper()

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgPoster: ImageView = view.findViewById(R.id.imgPoster)
        val movieTitle: TextView = view.findViewById(R.id.movieTitle)
        val tvRating: TextView = view.findViewById(R.id.tvRating)
        val tvOverview: TextView = view.findViewById(R.id.tvOverview)
        val tvLike: ImageView = view.findViewById(R.id.tvLike)
//        val tvDownload: ImageView = view.findViewById(R.id.tvDownload)
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
        Picasso.get()
            .load(imageUrl)
            .placeholder(R.drawable.placeholder_image)
            .into(holder.imgPoster)

        holder.movieTitle.text = movie.title
        holder.tvOverview.text = movie.overview
        holder.tvRating.text = "Rating: ${movie.vote_average}"

        holder.tvLike.setImageResource(
            if (isFavoriteScreen) R.drawable.ic_delete else R.drawable.ic_favorite
        )

        holder.tvLike.setOnClickListener {
            val entity = domainToEntity.map(dtoToDomain.map(movie))
            onIconClick(entity)
        }

//        holder.tvDownload.setOnClickListener {
//            val context = holder.itemView.context
//            Toast.makeText(context, "Downloading...", Toast.LENGTH_SHORT).show()
//            val intent = Intent(context, DownloadService::class.java).apply {
//                putExtra("movie_name", movie.title)
//            }
//            ContextCompat.startForegroundService(context, intent)
//        }
    }

}

