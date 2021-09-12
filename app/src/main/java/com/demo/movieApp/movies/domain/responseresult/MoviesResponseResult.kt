package com.demo.movieApp.movies.domain.responseresult

import com.demo.movieApp.movies.data.model.Movie
import com.demo.movieApp.movies.domain.NetworkState

data class MoviesResponseResult(
    val networkState: NetworkState,
    val movies: List<Movie>? = null
)