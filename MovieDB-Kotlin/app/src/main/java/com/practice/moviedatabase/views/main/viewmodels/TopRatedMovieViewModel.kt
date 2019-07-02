package com.practice.moviedatabase.views.main.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.practice.moviedatabase.base.BaseViewModel
import com.practice.moviedatabase.models.params.TopRatedMovieParams
import com.practice.moviedatabase.dal.repositories.TopRatedMovieRepository
import com.practice.moviedatabase.models.Genre
import com.practice.moviedatabase.models.Movie
import com.practice.moviedatabase.models.Result
import kotlinx.coroutines.launch

class TopRatedMovieViewModel(private var repository: TopRatedMovieRepository) : BaseViewModel() {

    val topRateMovieLiveData = MediatorLiveData<Result<List<Movie>>>()
    private val topRatedMovieParams = MediatorLiveData<TopRatedMovieParams>()

    private val genresLiveData: MutableLiveData<List<Genre>> = MutableLiveData()

    init {
        topRateMovieLiveData.addSource(topRatedMovieParams) {
            refreshTopRatedMovie(it)
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

    private fun refreshTopRatedMovie(params: TopRatedMovieParams) {

        uiScope.launch {

            val movies = topRateMovieLiveData.value

            if (movies != null) { return@launch }

            topRateMovieLiveData.value = Result.loading()

            val result = repository(params)
            topRateMovieLiveData.value = result
        }
    }
}
