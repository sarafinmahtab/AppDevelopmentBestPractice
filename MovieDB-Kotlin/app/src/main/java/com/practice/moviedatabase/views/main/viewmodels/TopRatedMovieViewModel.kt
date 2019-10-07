package com.practice.moviedatabase.views.main.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.practice.moviedatabase.base.BaseViewModel
import com.practice.moviedatabase.bll.GetConnectivityStatus
import com.practice.moviedatabase.bll.GetMovieGenres
import com.practice.moviedatabase.bll.GetTopRatedMovies
import com.practice.moviedatabase.bll.isNetworkAvailable
import com.practice.moviedatabase.models.Genres
import com.practice.moviedatabase.models.Result
import com.practice.moviedatabase.models.TopRatedMovie
import com.practice.moviedatabase.models.params.GenreParams
import com.practice.moviedatabase.models.params.TopRatedMovieParams
import kotlinx.coroutines.launch
import javax.inject.Inject


class TopRatedMovieViewModel @Inject constructor(
    private val getConnectivityStatus: GetConnectivityStatus,
    private val getTopRatedMovies: GetTopRatedMovies,
    private val getMovieGenres: GetMovieGenres
) : BaseViewModel() {

    val connectionLiveData = MutableLiveData<Boolean>()

    val genresLiveData = MutableLiveData<Result<Genres>>()

    val topRatedMovieLiveData = MediatorLiveData<Result<TopRatedMovie>>()
    private val topRatedMovieParams = MutableLiveData<TopRatedMovieParams>()

    init {
        topRatedMovieLiveData.addSource(topRatedMovieParams) {
            refreshTopRatedMovie(it)
        }
    }

    fun checkConnectionAvailable() {

        uiScope.launch {
            val connection = getConnectivityStatus(Any())
            connectionLiveData.value = connection.isNetworkAvailable()
        }
    }

    fun setParams(params: TopRatedMovieParams) {
        val lastParam = topRatedMovieParams.value
        if (lastParam != params) {
            topRatedMovieParams.value = params
        }
    }

    fun refresh() {
        val lastParam = topRatedMovieParams.value
        if (lastParam != null) {
            refreshTopRatedMovie(lastParam)
        }
    }

    public fun fetchGenres(params: GenreParams) {
        uiScope.launch {
            genresLiveData.value = Result.loading()

            val result = getMovieGenres(params)
            genresLiveData.value = result
        }
    }

    private fun refreshTopRatedMovie(params: TopRatedMovieParams) {

        uiScope.launch {

            topRatedMovieLiveData.value = Result.loading()

            val result = getTopRatedMovies(params)
            topRatedMovieLiveData.value = result
        }
    }
}
