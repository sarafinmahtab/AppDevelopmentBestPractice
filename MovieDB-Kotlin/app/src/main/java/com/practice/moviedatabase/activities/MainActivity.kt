package com.practice.moviedatabase.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.moviedatabase.R
import com.practice.moviedatabase.AllConstants
import com.practice.moviedatabase.adapters.MovieListAdapter
import com.practice.moviedatabase.base.ItemClickListener
import com.practice.moviedatabase.models.Result
import com.practice.moviedatabase.models.TopRatedMovie
import com.practice.moviedatabase.models.params.TopRatedMovieParams
import com.practice.moviedatabase.networks.ApiClient
import com.practice.moviedatabase.networks.ApiService
import com.practice.moviedatabase.repositories.TopRatedMovieRepository
import com.practice.moviedatabase.viewmodels.TopRatedMovieViewModel
import com.practice.moviedatabase.viewmodels.factories.TopRatedViewModelFactory

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), ItemClickListener {

    private lateinit var viewModel : TopRatedMovieViewModel
    private lateinit var adapter : MovieListAdapter

    private var checked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViewModel()
        initViews()
        dataObserver()
    }

    private fun dataObserver() {
        viewModel.topRateMovieLiveData.observe(this, Observer {
            if (it != null) {
                progressBar.visibility = View.GONE
                adapter.setTopRatedMovie(it)
                adapter.notifyDataSetChanged()
            }
        })
    }

    private fun initViews() {

        adapter = MovieListAdapter(this)
        adapter.setTopRatedMovie(TopRatedMovie())
        adapter.setItemClickListener(this)

        val layoutManager = LinearLayoutManager(this)
        movieListRecyclerView.layoutManager = layoutManager
        movieListRecyclerView.adapter = adapter

        detailsViewSwitch.isChecked = false

        detailsViewSwitch.setOnCheckedChangeListener { _, isChecked ->
            checked = isChecked
        }
    }

    private fun initViewModel() {
        progressBar.visibility = View.VISIBLE

        val apiService = ApiClient.client?.create(ApiService::class.java)
        val repository = TopRatedMovieRepository(apiService)

        viewModel = ViewModelProviders.of(this, TopRatedViewModelFactory(repository))
            .get(TopRatedMovieViewModel::class.java)

        viewModel.requestTopRatedMoviesApi(TopRatedMovieParams(getString(R.string.api_key), getString(R.string.language),
            getString(R.string.default_page), getString(R.string.sorted_by)))
    }

    override fun onItemClicked(result: Result, outputDate: String?) {

        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra("poster_url", AllConstants.BASE_IMAGE_URL + result.posterPath)
        intent.putExtra("title", result.title)
        intent.putExtra("release_date", outputDate)
        intent.putExtra("vote_average", result.voteAverage.toString())
        intent.putExtra("overview", result.overview)
        intent.putExtra("checked", checked)
        startActivity(intent)
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
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
