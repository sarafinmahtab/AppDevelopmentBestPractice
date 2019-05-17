package com.practice.moviedatabase.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.practice.moviedatabase.models.TopRatedMovie
import com.practice.moviedatabase.repositories.TopRatedMovieRepository
import javax.inject.Inject

class TopRatedMovieViewModel(@Inject private var repository: TopRatedMovieRepository) : ViewModel() {

    lateinit var topRateMovieLiveData: LiveData<TopRatedMovie>

    fun requestTopRatedMoviesApi(apiKey: String, language: String, page: String, sortedBy: String) {

        topRateMovieLiveData = repository.callTopRatedMoviesApi(apiKey, language, page, sortedBy)
    }
}
