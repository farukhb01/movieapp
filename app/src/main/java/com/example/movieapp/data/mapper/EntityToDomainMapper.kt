package com.example.movieapp.data.mapper

import com.example.movieapp.data.local.db.entity.MovieEntity
import com.example.movieapp.domain.mapper.Mapper
import com.example.movieapp.domain.model.Movie

class EntityToDomainMapper : Mapper<MovieEntity, Movie> {
    override fun map(input: MovieEntity): Movie {
        return Movie(
            id = input.id,
            title = input.title,
            overview = input.overview,
            posterPath = input.poster_path,
            voteAverage = input.vote_average
        )
    }
}
