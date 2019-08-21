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
import com.practice.moviedatabase.dal.networks.ServerConstants.sortedBy
import com.practice.moviedatabase.models.Genres
import com.practice.moviedatabase.models.Movie
import com.practice.moviedatabase.models.params.TopRatedMovieParams
import com.practice.moviedatabase.utilities.getGenreFromIds


class MovieListAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val movieOddHolderID: Int = 100
    private val movieEvenHolderID: Int = 101
    private var loaderHolderID = 102
    private var endHolderID = 103
    private var retryHolderID = 104

    private var totalPageSize = 0
    private var currentPageKey = 1

    private var requestErrorOccurred = false

    private var movieList: MutableList<Movie> = mutableListOf()

    private val genresHashMap = LinkedHashMap<Int, String>()

    private lateinit var clickListener: ItemClickListener
    private lateinit var pagingListener: PageLoadListener<TopRatedMovieParams>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val oddView = LayoutInflater.from(context)
            .inflate(R.layout.layout_movie_item_odd, parent, false)
        val evenView = LayoutInflater.from(context)
            .inflate(R.layout.layout_movie_item_even, parent, false)
        val loadingView = LayoutInflater.from(context)
            .inflate(R.layout.layout_movie_loader, parent, false)
        val endView = LayoutInflater.from(context)
            .inflate(R.layout.layout_item_end, parent, false)

        return when (viewType) {
            movieOddHolderID -> MovieOddListViewHolder(oddView).apply {
                setItemClickListener(clickListener)
            }
            movieEvenHolderID -> MovieEvenListViewHolder(evenView).apply {
                setItemClickListener(clickListener)
            }
            loaderHolderID -> MovieLoadingViewHolder(loadingView)
            else -> EndLoadingViewHolder(endView)
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when {
            holder.itemViewType == movieOddHolderID -> {

                val oddViewHolder: MovieOddListViewHolder = holder as MovieOddListViewHolder
                val result = movieList[position]

                if (result.genreIds.isNullOrEmpty()) {
                    oddViewHolder.bind(result, context.getString(R.string.no_matched_genres))
                } else {
                    oddViewHolder.bind(result, getGenreFromIds(genresHashMap, result.genreIds!!))
                }
            }
            holder.itemViewType == movieEvenHolderID -> {

                val evenViewHolder: MovieEvenListViewHolder = holder as MovieEvenListViewHolder
                val result = movieList[position]

                if (result.genreIds.isNullOrEmpty()) {
                    evenViewHolder.bind(result, context.getString(R.string.no_matched_genres))
                } else {
                    evenViewHolder.bind(result, getGenreFromIds(genresHashMap, result.genreIds!!))
                }
            }
            holder.itemViewType == loaderHolderID -> {
                pagingListener.loadNextPage(
                    TopRatedMovieParams(apiKey, language, (currentPageKey + 1).toString(), sortedBy)
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {

        return when (position) {
            movieList.size -> {
                when (currentPageKey) {
                    totalPageSize -> endHolderID
                    else -> loaderHolderID
                }
            }
            else -> {

                if (position.and(1) == 1) {
                    movieEvenHolderID
                } else {
                    movieOddHolderID
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return when {
            movieList.isNullOrEmpty() -> {
                1
            }
            else -> {
                movieList.size + 1
            }
        }
    }

    fun setTopRatedMovie(movies: MutableList<Movie>) {
        movieList = movies
        notifyDataSetChanged()
    }

    fun updateTopRatedMovie(movies: MutableList<Movie>) {
        currentPageKey += 1
        movieList.addAll(movies)

        notifyItemRangeInserted(
            movieList.size - movies.size, movies.size
        )
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

    fun setTotalPageSize(totalPageSize: Int) {
        this.totalPageSize = totalPageSize
    }

    fun setGenres(genres: Genres) {

        for (genre in genres.genres!!) {
            genresHashMap[genre.id] = genre.name
        }
    }
}
