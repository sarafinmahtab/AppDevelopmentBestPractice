package com.practice.moviedatabase.views.main.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.practice.moviedatabase.base.BaseViewModel
import com.practice.moviedatabase.bll.GetConnectivityStatus
import com.practice.moviedatabase.bll.GetMovieGenres
import com.practice.moviedatabase.bll.GetTopRatedMovies
import com.practice.moviedatabase.bll.isNetworkAvailable
import com.practice.moviedatabase.models.Genres
import com.practice.moviedatabase.helpers.ResourceHolder
import com.practice.moviedatabase.models.TopRatedMovie
import com.practice.moviedatabase.models.params.TopRatedMovieParams
import kotlinx.coroutines.launch
import javax.inject.Inject


class TopRatedMovieViewModel @Inject constructor(
    private val getConnectivityStatus: GetConnectivityStatus,
    private val getTopRatedMovies: GetTopRatedMovies,
    private val getMovieGenres: GetMovieGenres
) : BaseViewModel() {

    val connectionLiveData = MutableLiveData<Boolean>()

    val genresLiveData = MutableLiveData<ResourceHolder<Genres>>()

    val topRatedMovieLiveData = MediatorLiveData<ResourceHolder<TopRatedMovie>>()
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

    fun fetchGenres() {
        uiScope.launch {
            genresLiveData.value = ResourceHolder.loading()

            val result = getMovieGenres(Any())
            genresLiveData.value = result
        }
    }

    private fun refreshTopRatedMovie(params: TopRatedMovieParams) {

        uiScope.launch {

            topRatedMovieLiveData.value = ResourceHolder.loading()

            val result = getTopRatedMovies(params)
            topRatedMovieLiveData.value = result
        }
    }
}
