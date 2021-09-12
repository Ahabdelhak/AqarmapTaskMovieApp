package com.demo.movieApp.movies.view.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.demo.movieApp.movies.R
import com.demo.movieApp.movies.data.model.Movie
import com.demo.movieApp.movies.utils.GlideApp
import kotlinx.android.synthetic.main.movie_list_item.view.*


class MovieViewHolder(view: View, private val itemClickedCallback: (Movie?) -> Unit) : RecyclerView.ViewHolder(view) {

    fun bindTo(movie: Movie?) {

        itemView.title.text = movie?.title
        itemView.release.text =  movie?.releaseDate
        val imageUrl =  "https://image.tmdb.org/t/p/w200/" + movie?.backdropPath

        GlideApp.with(itemView.context)
            .load(imageUrl)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.color.gray)
            .error(R.color.gray)
            .into(itemView.preview)
        itemView.setOnClickListener{
            itemClickedCallback(movie)
        }

    }

    companion object {

        fun create(parent: ViewGroup, itemClickedCallback: (Movie?) -> Unit): MovieViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.movie_list_item, parent, false)
            return MovieViewHolder(view, itemClickedCallback)
        }
    }

}