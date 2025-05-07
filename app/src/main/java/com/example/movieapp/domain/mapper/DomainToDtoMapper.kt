package com.example.movieapp.domain.mapper

import com.example.movieapp.data.remote.api.MovieDto
import com.example.movieapp.domain.model.Movie

class DomainToDtoMapper : Mapper<Movie, MovieDto> {
    override fun map(input: Movie): MovieDto {
        return MovieDto(
            adult = false,
            backdrop_path = "",
            genre_ids = emptyList(),
            id = input.id,
            original_language = "",
            original_title = input.title,
            overview = input.overview,
            popularity = 0.0,
            poster_path = input.posterPath,
            release_date = "",
            title = input.title,
            video = false,
            vote_average = input.voteAverage,
            vote_count = 0
        )
    }
}