package com.practice.moviedatabase.views.main.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.practice.moviedatabase.R
import com.practice.moviedatabase.base.ItemClickListener
import com.practice.moviedatabase.dal.networks.ServerConstants
import com.practice.moviedatabase.dal.networks.ServerConstants.inputDateFormat
import com.practice.moviedatabase.dal.networks.ServerConstants.outputDateFormat
import com.practice.moviedatabase.models.Movie


class MovieOddListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val moviePosterImageView: ImageView = itemView.findViewById(R.id.moviePosterImageView)

    private val movieTitleTextView: TextView = itemView.findViewById(R.id.movieTitleTextView)
    private val releasedDateTextView: TextView = itemView.findViewById(R.id.movieReleasedTextView)

    private lateinit var listener: ItemClickListener

    fun bind(
        movie: Movie
    ) {
        val formattedDate = inputDateFormat.parse(movie.releaseDate!!)
        val outputDate = outputDateFormat.format(formattedDate!!)

        movieTitleTextView.text = movie.title
        releasedDateTextView.text = String.format("Released : %s", outputDate)

        Glide.with(itemView.context)
            .load(ServerConstants.BASE_IMAGE_URL + movie.posterPath)
            .placeholder(R.drawable.ic_movie_poster)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(moviePosterImageView)

        itemView.setOnClickListener {
            listener.onItemClicked(movie, outputDate)
        }
    }

    fun setItemClickListener(
        listener: ItemClickListener
    ) {
        this.listener = listener
    }
}


class MovieEvenListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val moviePosterImageView: ImageView = itemView.findViewById(R.id.moviePosterImageView)

    private val movieTitleTextView: TextView = itemView.findViewById(R.id.movieTitleTextView)
    private val releasedDateTextView: TextView = itemView.findViewById(R.id.movieReleasedTextView)

    private lateinit var listener: ItemClickListener

    fun bind(
        movie: Movie
    ) {
        val formattedDate = inputDateFormat.parse(movie.releaseDate!!)
        val outputDate = outputDateFormat.format(formattedDate!!)

        movieTitleTextView.text = movie.title
        releasedDateTextView.text = String.format("Released : %s", outputDate)

        Glide.with(itemView.context)
            .load(ServerConstants.BASE_IMAGE_URL + movie.posterPath)
            .placeholder(R.drawable.ic_movie_poster)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(moviePosterImageView)

        itemView.setOnClickListener {
            listener.onItemClicked(movie, outputDate)
        }
    }

    fun setItemClickListener(
        listener: ItemClickListener
    ) {
        this.listener = listener
    }
}


class MovieInitialLoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val movieLoadingGifImageView: AppCompatImageView = itemView.findViewById(R.id.movieLoadingGifImageView)

    fun load() {
        Glide.with(itemView.context)
            .asGif()
            .load(R.drawable.toy_story_loading_animation)
            .into(movieLoadingGifImageView)
    }
}


class MovieLoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
