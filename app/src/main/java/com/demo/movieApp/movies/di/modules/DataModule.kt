package com.demo.movieApp.movies.di.modules

import android.content.Context
import com.demo.movieApp.movies.data.repository.MoviesRepo
import com.demo.movieApp.movies.data.repository.local.DatabaseManager
import com.demo.movieApp.movies.data.repository.local.MovieDao
import com.demo.movieApp.movies.data.repository.remote.MovieAPI
import com.demo.movieApp.movies.domain.usecase.MovieUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class DataModule
@Inject constructor(context: Context) {

    private val databaseManager: DatabaseManager = DatabaseManager.getInstance(context)

    @Provides

    internal fun provideMovieDao()
            = databaseManager.movieDao()

    @Provides
    internal fun provideRepository(local: MovieDao, remote: MovieAPI)
            = MoviesRepo(local, remote)

    @Provides
    internal fun providesMovieUseCase(moviesRepo: MoviesRepo)
            = MovieUseCase(moviesRepo)

}