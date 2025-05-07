package com.example.movieapp.domain.mapper

import com.example.movieapp.data.local.db.entity.MovieEntity
import com.example.movieapp.domain.model.Movie

class DomainToEntityMapper : Mapper<Movie, MovieEntity> {
    override fun map(input: Movie): MovieEntity {
        return MovieEntity(
            id = input.id,
            title = input.title,
            overview = input.overview,
            poster_path = input.posterPath,
            vote_average = input.voteAverage
        )
    }
}