package com.practice.moviedatabase.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practice.moviedatabase.R
import com.practice.moviedatabase.AllConstants
import com.practice.moviedatabase.adapters.viewholders.MovieEvenListViewHolder
import com.practice.moviedatabase.base.ItemClickListener
import com.practice.moviedatabase.models.Result
import com.practice.moviedatabase.models.TopRatedMovie
import com.practice.moviedatabase.adapters.viewholders.MovieOddListViewHolder
import java.util.*

class MovieListAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val movieOddItem: Int = 0
    private val movieEvenItem: Int = 1

    private var topRatedMovie: TopRatedMovie? = null
    private var movieList: List<Result>? = null

    private lateinit var listener: ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val oddView = LayoutInflater.from(context).inflate(R.layout.layout_movie_item_odd, parent, false)
        val evenView = LayoutInflater.from(context).inflate(R.layout.layout_movie_item_even, parent, false)

        return when (viewType) {
            movieOddItem -> MovieOddListViewHolder(oddView)
            else -> MovieEvenListViewHolder(evenView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder.itemViewType == movieOddItem) {

            val oddViewHolder: MovieOddListViewHolder = holder as MovieOddListViewHolder
            val result = movieList!![position]

            oddViewHolder.bind(context, result, listener)
        } else {

            val evenViewHolder: MovieEvenListViewHolder = holder as MovieEvenListViewHolder
            val result = movieList!![position]

            evenViewHolder.bind(context, result, listener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position.and(1) == 1) movieOddItem else movieEvenItem
    }

    override fun getItemCount(): Int {
        return movieList!!.size
    }

    fun setTopRatedMovie(topRatedMovie: TopRatedMovie) {
        this.topRatedMovie = topRatedMovie

        if (topRatedMovie.results == null) {
            this.movieList = ArrayList()
        } else {
            this.movieList = topRatedMovie.results
        }
    }

    fun setItemClickListener(listener: ItemClickListener) {
        this.listener = listener
    }
}
