package com.practice.moviedatabase.views.main.viewmodels

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.practice.moviedatabase.base.BaseViewModel
import com.practice.moviedatabase.models.TopRatedMovie
import com.practice.moviedatabase.models.params.TopRatedMovieParams
import com.practice.moviedatabase.dal.repositories.TopRatedMovieRepository
import kotlinx.coroutines.launch

class TopRatedMovieViewModel(private var repository: TopRatedMovieRepository) : BaseViewModel() {

    val topRateMovieLiveData: MediatorLiveData<TopRatedMovie> = MediatorLiveData()
    val _param = MediatorLiveData<TopRatedMovieParams>()

    init {
        topRateMovieLiveData.addSource(_param) {
            refreshTopRatedMovie(it)
        }
    }


    fun init(params: TopRatedMovieParams) {
        val lastParam = _param.value
        if (lastParam != params) {
            _param.value = params
        }
    }

    fun refresh() {
        val lastParam = _param.value
        if (lastParam != null) {
            refreshTopRatedMovie(lastParam)
        }
    }


    private fun refreshTopRatedMovie(params: TopRatedMovieParams) {

        uiScope.launch {

            val topRatedMovie = topRateMovieLiveData.value

            if (topRatedMovie?.movies != null) {

                Log.d("ViewModel", "Returning previous TopRatedMovie object")

                return@launch
            }

            Log.d("ViewModel", "Initializing new TopRatedMovie object")

            val result = repository(params)
            topRateMovieLiveData.value = result
        }
    }
}
