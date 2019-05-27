package com.practice.moviedatabase.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.moviedatabase.R
import com.practice.moviedatabase.adapters.MovieListAdapter
import com.practice.moviedatabase.models.TopRatedMovie
import com.practice.moviedatabase.networks.ApiClient
import com.practice.moviedatabase.networks.ApiService
import com.practice.moviedatabase.repositories.TopRatedMovieRepository
import com.practice.moviedatabase.viewmodels.TopRatedMovieViewModel
import com.practice.moviedatabase.viewmodels.factories.TopRatedViewModelFactory

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : TopRatedMovieViewModel
    private lateinit var adapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViewModel()
        initRecyclerView()
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

    private fun initRecyclerView() {

        adapter = MovieListAdapter(this)
        adapter.setTopRatedMovie(TopRatedMovie())

        val layoutManager = LinearLayoutManager(this)
        movieListRecyclerView.layoutManager = layoutManager
        movieListRecyclerView.adapter = adapter
    }

    private fun initViewModel() {
        progressBar.visibility = View.VISIBLE

        val apiService = ApiClient.client?.create(ApiService::class.java)
        val repository = TopRatedMovieRepository(apiService)

        viewModel = ViewModelProviders.of(this, TopRatedViewModelFactory(repository))
            .get(TopRatedMovieViewModel::class.java)

        viewModel.requestTopRatedMoviesApi(
            getString(R.string.api_key), getString(R.string.language),
            getString(R.string.default_page), getString(R.string.sorted_by))
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
