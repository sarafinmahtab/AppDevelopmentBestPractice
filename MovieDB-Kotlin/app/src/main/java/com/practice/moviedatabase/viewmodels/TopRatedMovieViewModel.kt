package com.practice.moviedatabase.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.practice.moviedatabase.base.BaseViewModel
import com.practice.moviedatabase.models.TopRatedMovie
import com.practice.moviedatabase.repositories.TopRatedMovieRepository
import kotlinx.coroutines.launch

class TopRatedMovieViewModel(private var repository: TopRatedMovieRepository) : BaseViewModel() {

    val topRateMovieLiveData: MutableLiveData<TopRatedMovie> = MutableLiveData()

    fun requestTopRatedMoviesApi(apiKey: String, language: String, page: String, sortedBy: String) {

        uiScope.launch {

            val topRatedMovie = topRateMovieLiveData.value

            if (topRatedMovie?.results != null) {

                Log.d("ViewModel", "Returning previous TopRatedMovie object")

                return@launch
            }

            Log.d("ViewModel", "Initializing new TopRatedMovie object")

            val result  = repository.callTopRatedMoviesApi(apiKey, language, page, sortedBy)
            topRateMovieLiveData.value = result
        }
    }
}
