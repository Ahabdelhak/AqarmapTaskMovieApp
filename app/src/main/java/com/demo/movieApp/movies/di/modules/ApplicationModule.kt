package com.demo.movieApp.movies.di.modules

import android.content.Context
import com.demo.movieApp.movies.di.scopes.MovieApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val context: Context) {

    @Provides
    @MovieApplicationScope
    internal fun provideContext() = context
}