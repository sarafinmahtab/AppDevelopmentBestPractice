package com.practice.moviedatabase.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.practice.moviedatabase.models.TopRatedMovie
import com.practice.moviedatabase.repositories.TopRatedMovieRepository

class TopRatedMovieViewModel(private var repository: TopRatedMovieRepository) : ViewModel() {

    var topRateMovieLiveData: LiveData<TopRatedMovie>? = null

    fun requestTopRatedMoviesApi(apiKey: String, language: String, page: String, sortedBy: String) {

        if (topRateMovieLiveData != null) {
            // ViewModel is created on a per-Fragment basis, so the userId
            // doesn't change.

            Log.d("ViewModel", "Returning current TopRatedMovie object")

            return
        }

        Log.d("ViewModel", "Initializing new TopRatedMovie object")

        topRateMovieLiveData = repository.callTopRatedMoviesApi(apiKey, language, page, sortedBy)
    }
}
