package com.demo.movieApp.movies.di.modules

import androidx.lifecycle.ViewModel
import com.demo.movieApp.movies.di.scopes.ViewModelKey
import com.demo.movieApp.movies.view.movies.MovieListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    abstract fun bindMovieListViewModel(viewModel: MovieListViewModel): ViewModel


}