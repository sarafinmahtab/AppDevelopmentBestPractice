package com.practice.moviedatabase.views.main.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.practice.moviedatabase.base.BaseViewModel
import com.practice.moviedatabase.models.TopRatedMovie
import com.practice.moviedatabase.models.params.TopRatedMovieParams
import com.practice.moviedatabase.dal.repositories.TopRatedMovieRepository
import kotlinx.coroutines.launch

class TopRatedMovieViewModel(private var repository: TopRatedMovieRepository) : BaseViewModel() {

    val topRateMovieLiveData: MutableLiveData<TopRatedMovie> = MutableLiveData()

    fun requestTopRatedMoviesApi(params: TopRatedMovieParams) {
        refreshTopRatedMovie(params)
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
