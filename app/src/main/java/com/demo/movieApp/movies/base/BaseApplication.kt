package com.demo.movieApp.movies.base

import android.app.Application
import com.demo.movieApp.movies.di.components.ApplicationComponent
import com.demo.movieApp.movies.BuildConfig
import com.demo.movieApp.movies.di.components.DaggerApplicationComponent
import com.demo.movieApp.movies.di.modules.ApplicationModule
import com.demo.movieApp.movies.di.modules.DataModule
import com.demo.movieApp.movies.di.modules.NetworkModule

class BaseApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    @Suppress("DEPRECATION")
    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .networkModule(NetworkModule())
            .dataModule(DataModule(this))
            .applicationModule(ApplicationModule(this))
            .build()

    }

}