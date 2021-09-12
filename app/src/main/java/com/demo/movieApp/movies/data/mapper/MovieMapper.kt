package com.demo.movieApp.movies.data.mapper

import com.demo.movieApp.movies.data.model.Movie
import com.demo.movieApp.movies.data.model.MovieEntity

class MovieMapper {
    fun fromDb(from: MovieEntity) = Movie(
        from.id,
        from.title,
        from.overview,
        from.releaseDate,
        from.backdropPath
    )

    fun toDb(from: Movie) = MovieEntity(
        from.id,
        from.overview,
        from.title,
        from.releaseDate,
        from.backdropPath
    )
}