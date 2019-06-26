package com.practice.moviedatabase.views.main.viewmodels

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import com.practice.moviedatabase.base.BaseViewModel
import com.practice.moviedatabase.models.params.TopRatedMovieParams
import com.practice.moviedatabase.dal.repositories.TopRatedMovieRepository
import com.practice.moviedatabase.models.Movie
import com.practice.moviedatabase.models.Result
import kotlinx.coroutines.launch

class TopRatedMovieViewModel(private var repository: TopRatedMovieRepository) : BaseViewModel() {

    private val tag = this.javaClass.simpleName

    val topRateMovieLiveData = MediatorLiveData<Result<List<Movie>>>()
    private val topRatedMovieParams = MediatorLiveData<TopRatedMovieParams>()

    init {
        topRateMovieLiveData.addSource(topRatedMovieParams) {
            refreshTopRatedMovie(it)
        }
    }


    fun init(params: TopRatedMovieParams) {
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

            if (movies != null) {

                Log.d(tag, "Returning previous TopRatedMovie object")

                return@launch
            }

            Log.d(tag, "Initializing new TopRatedMovie object")

            topRateMovieLiveData.value = Result.loading()
            val result = repository(params)

            Log.d(tag, result.data?.size.toString())

            topRateMovieLiveData.value = result
        }
    }
}
