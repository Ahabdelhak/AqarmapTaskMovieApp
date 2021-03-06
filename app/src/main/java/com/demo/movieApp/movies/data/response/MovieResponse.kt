package com.demo.movieApp.movies.data.response

import com.demo.movieApp.movies.data.model.MovieEntity
import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("results")
    val results: List<MovieEntity>? = null,

    @field:SerializedName("total_results")
    val totalResults: Int? = null
)