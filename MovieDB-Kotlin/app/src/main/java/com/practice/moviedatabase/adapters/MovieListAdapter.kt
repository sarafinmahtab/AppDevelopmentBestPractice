package com.practice.moviedatabase.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practice.moviedatabase.R
import com.practice.moviedatabase.Urls
import com.practice.moviedatabase.activities.MovieDetailsActivity
import com.practice.moviedatabase.models.Result
import com.practice.moviedatabase.models.TopRatedMovie

import java.util.ArrayList

class MovieListAdapter(private val context: Context) : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {
    private var topRatedMovie: TopRatedMovie? = null
    private var movieList: List<Result>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.layout_movie_item, parent, false)

        return MovieListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val result = movieList!![position]

        holder.movieTitleTextView.text = result.title
        holder.releasedDateTextView.text = String.format("Released : %s", result.releaseDate)

        Glide.with(context)
            .load(Urls.BASE_IMAGE_URL + result.posterPath)
            .placeholder(R.drawable.ic_movie_poster)
            .into(holder.moviePosterImageView)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra("poster_url", Urls.BASE_IMAGE_URL + result.posterPath)
            intent.putExtra("title", result.title)
            intent.putExtra("release_date", result.releaseDate)
            intent.putExtra("vote_average", result.voteAverage.toString())
            intent.putExtra("overview", result.overview)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return movieList!!.size
    }

    inner class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val moviePosterImageView: ImageView = itemView.findViewById(R.id.moviePosterImageView)

        val movieTitleTextView: TextView = itemView.findViewById(R.id.movieTitleTextView)
        val releasedDateTextView: TextView = itemView.findViewById(R.id.movieReleasedTextView)
    }

    fun setTopRatedMovie(topRatedMovie: TopRatedMovie) {
        this.topRatedMovie = topRatedMovie

        if (topRatedMovie.results == null) {
            this.movieList = ArrayList()
        } else {
            this.movieList = topRatedMovie.results
        }
    }
}
