package com.demo.movieApp.movies.data.repository

import com.demo.movieApp.movies.data.mapper.MovieMapper
import com.demo.movieApp.movies.data.model.Movie
import com.demo.movieApp.movies.data.repository.local.MovieDao
import com.demo.movieApp.movies.data.repository.remote.MovieAPI
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepo
@Inject
constructor(private val local: MovieDao,
            private val remote: MovieAPI
) {

    private val mapper = MovieMapper()

    fun getMovies(pageNumber: Int): Flowable<List<Movie>?> {

        val remoteMovie =
            remote.getMovies(pageNumber).map { movieResponse ->
                movieResponse.results?.map { movieEntity ->
                    if (pageNumber == 1)
                        local.insertMovie(movieEntity)
                    mapper.fromDb(movieEntity)
                }
            }


        return remoteMovie.toFlowable()
    }

 @Suppress("unused")
    fun clearDatabase(): Single<Int> {
        return Observable.fromCallable { local.deleteAll() }.firstOrError()
    }



}