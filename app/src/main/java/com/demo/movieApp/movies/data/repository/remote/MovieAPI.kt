package com.demo.movieApp.movies.data.repository.remote

import com.demo.movieApp.movies.data.response.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("/3/movie/popular?api_key=bd6ed26d5145c579674f4fe8a390a01b")
    fun getMovies(@Query("page") pageNumber: Int): Single<MovieResponse>
}