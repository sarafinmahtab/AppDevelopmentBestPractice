package com.practice.moviedatabase.views.main.viewmodels

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.practice.moviedatabase.base.BaseViewModel
import com.practice.moviedatabase.bll.GetMovieGenres
import com.practice.moviedatabase.bll.GetTopRatedMovies
import com.practice.moviedatabase.models.Genres
import com.practice.moviedatabase.models.Movie
import com.practice.moviedatabase.models.Result
import com.practice.moviedatabase.models.params.GenreParams
import com.practice.moviedatabase.models.params.TopRatedMovieParams
import kotlinx.coroutines.launch


class TopRatedMovieViewModel(
    private val getTopRatedMovies: GetTopRatedMovies,
    private val getMovieGenres: GetMovieGenres
) : BaseViewModel() {

    val genresLiveData = MutableLiveData<Result<Genres>>()

    val topRatedMovieLiveData = MediatorLiveData<Result<List<Movie>>>()
    private val topRatedMovieParams = MutableLiveData<TopRatedMovieParams>()

    init {
        topRatedMovieLiveData.addSource(topRatedMovieParams) {
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

    public fun fetchGenres(params: GenreParams) {
        uiScope.launch {
            genresLiveData.value = Result.loading()

            val result = getMovieGenres(params)
            genresLiveData.value = result
        }
    }

    private fun refreshTopRatedMovie(params: TopRatedMovieParams) {

        uiScope.launch {

            Log.d("PageLoading", params.page)

            topRatedMovieLiveData.value = Result.loading()

            val result = getTopRatedMovies(params)
            topRatedMovieLiveData.value = result
        }
    }
}
