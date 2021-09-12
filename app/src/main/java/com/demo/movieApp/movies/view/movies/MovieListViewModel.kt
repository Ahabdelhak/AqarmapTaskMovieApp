package com.demo.movieApp.movies.view.movies

import androidx.lifecycle.ViewModel
import com.demo.movieApp.movies.domain.usecase.MovieUseCase
import javax.inject.Inject

class MovieListViewModel
@Inject
constructor(private val useCase: MovieUseCase)
    : ViewModel() {

    var moviesLiveData = useCase.moviesLiveData()


    fun fetchMovies(pageNumber: Int) = useCase.getMovies(pageNumber)

    fun cleanObservables() = useCase.clear()

    override fun onCleared() {
        super.onCleared()
        cleanObservables()
    }



}