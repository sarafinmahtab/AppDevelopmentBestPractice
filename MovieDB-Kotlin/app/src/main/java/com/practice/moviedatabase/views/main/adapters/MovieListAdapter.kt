package com.practice.moviedatabase.views.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practice.moviedatabase.R
import com.practice.moviedatabase.views.main.adapters.viewholders.MovieEvenListViewHolder
import com.practice.moviedatabase.base.ItemClickListener
import com.practice.moviedatabase.models.Movie
import com.practice.moviedatabase.views.main.adapters.viewholders.MovieOddListViewHolder

class MovieListAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val movieOddItem: Int = 0
    private val movieEvenItem: Int = 1

    private var movieList: List<Movie> = arrayListOf()

    private lateinit var listener: ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val oddView = LayoutInflater.from(context).inflate(R.layout.layout_movie_item_odd, parent, false)
        val evenView = LayoutInflater.from(context).inflate(R.layout.layout_movie_item_even, parent, false)

        return when (viewType) {
            movieOddItem -> MovieOddListViewHolder(oddView).apply {
                setItemClickListener(listener)
            }
            else -> MovieEvenListViewHolder(evenView).apply {
                setItemClickListener(listener)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder.itemViewType == movieOddItem) {

            val oddViewHolder: MovieOddListViewHolder = holder as MovieOddListViewHolder
            val result = movieList[position]

            oddViewHolder.bind(result)
        } else {

            val evenViewHolder: MovieEvenListViewHolder = holder as MovieEvenListViewHolder
            val result = movieList[position]

            evenViewHolder.bind(result)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position.and(1) == 1) movieOddItem else movieEvenItem
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setTopRatedMovie(movies: List<Movie>) {
        movieList = movies
        notifyDataSetChanged()
    }

    fun setItemClickListener(listener: ItemClickListener) {
        this.listener = listener
    }
}
