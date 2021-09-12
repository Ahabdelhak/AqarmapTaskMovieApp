package com.demo.movieApp.movies.view.movies

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.demo.movieApp.movies.R
import com.demo.movieApp.movies.base.BaseApplication
import com.demo.movieApp.movies.base.ViewModelFactory
import com.demo.movieApp.movies.domain.NetworkState
import com.demo.movieApp.movies.view.BaseActivity
import com.demo.movieApp.movies.view.movies.adapter.MovieAdapter
import com.demo.movieApp.movies.utils.Dialogs
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MovieListViewModel
    private lateinit var adapter: MovieAdapter


    override fun injectActivity() {
        (application as? BaseApplication)?.applicationComponent?.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieListViewModel::class.java)

        initAdapter()
        initSwipeToRefresh()
        observeMoviesList()
        fetchMovies()

    }

    private fun initAdapter() {
        adapter = MovieAdapter(ArrayList()) { movie ->

            Dialogs.showMovieDetails(this,movie)

        }
        movies_list_view.adapter = adapter

    }

    private fun initSwipeToRefresh() {
        movies_swipe_refresh_layout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent)

        movies_swipe_refresh_layout.setOnRefreshListener {
            fetchMovies()
        }
    }

    private fun observeMoviesList() {
        viewModel.moviesLiveData.observe(this, Observer { moviesLiveData ->
            movies_swipe_refresh_layout.post {
                movies_swipe_refresh_layout.isRefreshing =
                    moviesLiveData.networkState.status == NetworkState.LOADING.status
            }
            when (moviesLiveData.networkState) {
                NetworkState.LOADED -> {
                    adapter.add(moviesLiveData.movies)
                    showEmptyList(moviesLiveData.movies?.isEmpty() ?: false)
                }
                NetworkState.LOADING -> {
                    // Loading
                }
                else -> {
                    showEmptyList(moviesLiveData.networkState.message != null, moviesLiveData.networkState.message)
                }
            }
        })

    }

    private fun fetchMovies(pageNumber: Int = 1) {
        adapter.clear()
        viewModel.fetchMovies(pageNumber)
    }



    private fun showEmptyList(show: Boolean, message: String? = null) {
        message?.let {
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
            empty_list.text = it
        }
        if (show && adapter.getMovies().isEmpty()) {
            empty_list.visibility = View.VISIBLE
            movies_list_view.visibility = View.GONE
        } else {
            empty_list.visibility = View.GONE
            movies_list_view.visibility = View.VISIBLE
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        viewModel.cleanObservables()
    }
}