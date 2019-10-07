package com.practice.moviedatabase.views.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.moviedatabase.R
import com.practice.moviedatabase.dal.PageLoadListener
import com.practice.moviedatabase.dal.networks.ServerConstants
import com.practice.moviedatabase.helpers.ItemClickListener
import com.practice.moviedatabase.helpers.ResourceHolder
import com.practice.moviedatabase.helpers.viewModelProvider
import com.practice.moviedatabase.models.Genres
import com.practice.moviedatabase.models.Movie
import com.practice.moviedatabase.models.TopRatedMovie
import com.practice.moviedatabase.models.params.TopRatedMovieParams
import com.practice.moviedatabase.views.details.MovieDetailsActivity
import com.practice.moviedatabase.views.details.MovieShortDetailsActivity
import com.practice.moviedatabase.views.main.adapters.MovieListAdapter
import com.practice.moviedatabase.views.main.viewmodels.TopRatedMovieViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity(),
    ItemClickListener,
    PageLoadListener<TopRatedMovieParams> {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: TopRatedMovieViewModel

    private lateinit var adapter: MovieListAdapter

    private var checked = false
    private var currentPage = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()

        viewModel = viewModelProvider(viewModelFactory)

        viewModelObservers()
        initViews()
    }

    private fun initViews() {

        adapter = MovieListAdapter(this)
        adapter.setItemClickListener(this)
        adapter.setPagingListener(this)

        movieListRecyclerView.layoutManager = LinearLayoutManager(this)
        movieListRecyclerView.adapter = adapter
    }


    private fun viewModelObservers() {

        viewModel.fetchGenres()

        viewModel.genresLiveData.observe(this@MainActivity, Observer {
            handleGenresData(it)
        })

        viewModel.topRatedMovieLiveData.observe(this@MainActivity, Observer {
            handleMoviesData(it)
        })
    }


    private fun handleGenresData(resourceHolder: ResourceHolder<Genres>) {
        when (resourceHolder.status) {
            ResourceHolder.Status.LOADING -> {

            }

            ResourceHolder.Status.SUCCESS -> {
                progressBar.visibility = View.GONE
                adapter.setGenres(resourceHolder.data!!)
            }

            ResourceHolder.Status.ERROR -> {
                progressBar.visibility = View.GONE
                Toast.makeText(this, "Error fetching genres", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun loadFirstPage(params: TopRatedMovieParams) {
        currentPage = 1
        viewModel.setParams(params)
    }

    override fun loadNextPage(params: TopRatedMovieParams) {
        currentPage = params.page.toInt()
        viewModel.setParams(params)
    }

    private fun handleMoviesData(resourceHolder: ResourceHolder<TopRatedMovie>) {
        when (resourceHolder.status) {
            ResourceHolder.Status.LOADING -> {

            }

            ResourceHolder.Status.SUCCESS -> {

                adapter.setTotalPageSize(resourceHolder.data?.totalPages ?: currentPage)

                if (currentPage == 1) {
                    adapter.setTopRatedMovie(resourceHolder.data?.movies as ArrayList<Movie>)
                } else {
                    adapter.updateTopRatedMovie(resourceHolder.data?.movies as ArrayList<Movie>)
                }
            }

            ResourceHolder.Status.ERROR -> {
                Toast.makeText(this, "An Error Occurred", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onItemClicked(movie: Movie, outputDate: String?) {

        if (checked) {
            val intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra("backdrop_url", ServerConstants.BASE_IMAGE_URL + movie.backdropPath)
            startNextActivity(movie, outputDate, intent)
        } else {
            val intent = Intent(this, MovieShortDetailsActivity::class.java)
            startNextActivity(movie, outputDate, intent)
        }
    }

    private fun startNextActivity(movie: Movie, outputDate: String?, intent: Intent) {
        intent.putExtra("poster_url", ServerConstants.BASE_IMAGE_URL + movie.posterPath)
        intent.putExtra("title", movie.title)
        intent.putExtra("release_date", outputDate)
        intent.putExtra("vote_average", movie.voteAverage.toString())
        intent.putExtra("overview", movie.overview)
        startActivity(intent)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""

        toolbarTitleTextView.text = getString(R.string.app_name)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                if (checked) {
                    checked = false
                    item.icon =
                        ResourcesCompat.getDrawable(resources, R.drawable.ic_simple_glass, null)
                } else {
                    checked = true
                    item.icon =
                        ResourcesCompat.getDrawable(resources, R.drawable.ic_color_glass, null)
                }
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}
