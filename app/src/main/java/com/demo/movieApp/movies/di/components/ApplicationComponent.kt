package com.demo.movieApp.movies.di.components

import com.demo.movieApp.movies.di.modules.ApplicationModule
import com.demo.movieApp.movies.di.modules.DataModule
import com.demo.movieApp.movies.di.modules.NetworkModule
import com.demo.movieApp.movies.di.modules.ViewModelModule
import com.demo.movieApp.movies.di.scopes.MovieApplicationScope
import com.demo.movieApp.movies.view.movies.MainActivity
import dagger.Component

@MovieApplicationScope
@Component(modules = [
    NetworkModule::class,
    DataModule::class,
    ApplicationModule::class,
    ViewModelModule::class
])
interface ApplicationComponent {

    fun inject(mainActivity : MainActivity)

}