package com.practice.moviedatabase.views.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practice.moviedatabase.R
import com.practice.moviedatabase.base.ItemClickListener
import com.practice.moviedatabase.dal.PageLoadListener
import com.practice.moviedatabase.dal.networks.ServerConstants.apiKey
import com.practice.moviedatabase.dal.networks.ServerConstants.language
import com.practice.moviedatabase.dal.networks.ServerConstants.pageKey
import com.practice.moviedatabase.dal.networks.ServerConstants.sortedBy
import com.practice.moviedatabase.models.Movie
import com.practice.moviedatabase.models.params.TopRatedMovieParams


class MovieListAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val movieOddHolderID: Int = 0
    private val movieEvenHolderID: Int = 1
    private val movieLoadHolderID: Int = 2
    private val movieInitialLoadHolderID = 3

    private var movieList: MutableList<Movie> = mutableListOf()

    private lateinit var clickListener: ItemClickListener
    private lateinit var pagingListener: PageLoadListener<TopRatedMovieParams>

    private var currentPageKey = pageKey


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val oddView = LayoutInflater.from(context)
            .inflate(R.layout.layout_movie_item_odd, parent, false)
        val evenView = LayoutInflater.from(context)
            .inflate(R.layout.layout_movie_item_even, parent, false)
        val loadingView = LayoutInflater.from(context)
            .inflate(R.layout.layout_movie_loader, parent, false)
        val initialLoadingView = LayoutInflater.from(context)
            .inflate(R.layout.layout_initial_movie_loading, parent, false)

        return when (viewType) {
            movieOddHolderID -> MovieOddListViewHolder(oddView).apply {
                setItemClickListener(clickListener)
            }
            movieEvenHolderID -> MovieEvenListViewHolder(evenView).apply {
                setItemClickListener(clickListener)
            }
            movieLoadHolderID -> MovieLoadingViewHolder(loadingView)
            else -> MovieInitialLoadingViewHolder(initialLoadingView)
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when {
            holder.itemViewType == movieOddHolderID -> {

                val oddViewHolder: MovieOddListViewHolder = holder as MovieOddListViewHolder
                val result = movieList[position]

                oddViewHolder.bind(result)
            }
            holder.itemViewType == movieEvenHolderID -> {

                val evenViewHolder: MovieEvenListViewHolder = holder as MovieEvenListViewHolder
                val result = movieList[position]

                evenViewHolder.bind(result)
            }
            holder.itemViewType == movieLoadHolderID -> {
                currentPageKey += 1
                pagingListener.loadNextPage(
                    TopRatedMovieParams(apiKey, language, currentPageKey.toString(), sortedBy)
                )
            }
            holder.itemViewType == movieInitialLoadHolderID -> {
                val initLoadViewHolder: MovieInitialLoadingViewHolder = holder as MovieInitialLoadingViewHolder
                initLoadViewHolder.load()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {

        return when {
            (position == movieList.size && movieList.size != 0) -> movieLoadHolderID
            (position.and(1) == 1 && position != movieList.size) -> movieEvenHolderID
            (position.and(1) == 0 && position != movieList.size) -> movieOddHolderID
            else -> movieInitialLoadHolderID
        }
    }

    override fun getItemCount(): Int {
        return movieList.size + 1
    }

    fun setTopRatedMovie(movies: MutableList<Movie>) {
        movieList = movies
        notifyDataSetChanged()
    }

    fun updateTopRatedMovie(movies: MutableList<Movie>) {
        movieList.addAll(movies)
        notifyDataSetChanged()
    }

    fun setItemClickListener(listener: ItemClickListener) {
        this.clickListener = listener
    }

    fun setPagingListener(listener: PageLoadListener<TopRatedMovieParams>) {
        this.pagingListener = listener
        this.pagingListener.loadFirstPage(
            TopRatedMovieParams(apiKey, language, currentPageKey.toString(), sortedBy)
        )
    }
}
