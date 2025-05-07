package com.example.movieapp.data.mapper

import com.example.movieapp.data.remote.api.MovieDto
import com.example.movieapp.domain.mapper.Mapper
import com.example.movieapp.domain.model.Movie

class DtoToDomainMapper : Mapper<MovieDto, Movie> {
    override fun map(input: MovieDto): Movie {
        return Movie(
            id = input.id,
            title = input.title,
            overview = input.overview,
            posterPath = input.poster_path,
            voteAverage = input.vote_average
        )
    }
}
