package com.practice.moviedatabase.views.main.adapters.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.practice.moviedatabase.utilities.ServerConstants
import com.practice.moviedatabase.utilities.ServerConstants.inputDateFormat
import com.practice.moviedatabase.utilities.ServerConstants.outputDateFormat
import com.practice.moviedatabase.R
import com.practice.moviedatabase.base.ItemClickListener
import com.practice.moviedatabase.models.Movie

class MovieEvenListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val moviePosterImageView: ImageView = itemView.findViewById(R.id.moviePosterImageView)

    private val movieTitleTextView: TextView = itemView.findViewById(R.id.movieTitleTextView)
    private val releasedDateTextView: TextView = itemView.findViewById(R.id.movieReleasedTextView)

    private lateinit var listener: ItemClickListener

    fun bind(
        movie: Movie
    ) {
        val formattedDate = inputDateFormat.parse(movie.releaseDate)
        val outputDate = outputDateFormat.format(formattedDate)

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
