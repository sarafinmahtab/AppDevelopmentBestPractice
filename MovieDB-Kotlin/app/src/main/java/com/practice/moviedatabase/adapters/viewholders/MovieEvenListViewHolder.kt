package com.practice.moviedatabase.adapters.viewholders

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practice.moviedatabase.AllConstants
import com.practice.moviedatabase.AllConstants.inputDateFormat
import com.practice.moviedatabase.AllConstants.outputDateFormat
import com.practice.moviedatabase.R
import com.practice.moviedatabase.base.ItemClickListener
import com.practice.moviedatabase.models.Result

class MovieEvenListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val moviePosterImageView: ImageView = itemView.findViewById(R.id.moviePosterImageView)

    private val movieTitleTextView: TextView = itemView.findViewById(R.id.movieTitleTextView)
    private val releasedDateTextView: TextView = itemView.findViewById(R.id.movieReleasedTextView)

    fun bind(
        context: Context,
        result: Result,
        listener: ItemClickListener
    ) {
        val formattedDate = inputDateFormat.parse(result.releaseDate)
        val outputDate = outputDateFormat.format(formattedDate)

        movieTitleTextView.text = result.title
        releasedDateTextView.text = String.format("Released : %s", outputDate)

        Glide.with(context)
            .load(AllConstants.BASE_IMAGE_URL + result.posterPath)
            .placeholder(R.drawable.ic_movie_poster)
            .into(moviePosterImageView)

        itemView.setOnClickListener {
            listener.onItemClicked(result, outputDate)
        }
    }
}
