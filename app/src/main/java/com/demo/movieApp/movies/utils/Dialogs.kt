package com.demo.movieApp.movies.utils

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.LinearLayout
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.demo.movieApp.movies.R
import com.demo.movieApp.movies.data.model.Movie
import kotlinx.android.synthetic.main.dialog_details.*


class Dialogs {
    companion object {
        var dialog: Dialog? = null

        fun showMovieDetails(
            context: Context,
            movie: Movie?
        ) {
            if (dialog != null && dialog?.isShowing!!) {
                dialog?.dismiss()
            }
            dialog = Dialog(context)
            dialog?.getWindow()?.setWindowAnimations(R.style.DialogAnimation);

            dialog?.window?.setBackgroundDrawable(ColorDrawable(0))
            dialog?.window?.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            dialog?.setContentView(R.layout.dialog_details)

            dialog?.tv_details?.text=movie?.title
            dialog?.txtMovieDetails?.text=movie?.overview
            GlideApp.with(context)
                .load("https://image.tmdb.org/t/p/w200/" + movie?.backdropPath)
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.color.colorPrimary)
                .error(R.color.colorPrimary)
                .centerCrop()
                .into(dialog?.movieimage!!)

            dialog?.ibtnCloseDialog?.setOnClickListener {
                dialog?.dismiss()
            }

            dialog?.setCancelable(false)
            dialog?.setCanceledOnTouchOutside(false)
            dialog?.show()
            val window: Window = dialog!!.window!!
            window.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        }


    }
}