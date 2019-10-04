package com.practice.moviedatabase.views.main.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practice.moviedatabase.bll.GetMovieGenres
import com.practice.moviedatabase.bll.GetTopRatedMovies
import com.practice.moviedatabase.views.main.viewmodels.TopRatedMovieViewModel

class TopRatedViewModelFactory(
    private val getTopRatedMovies: GetTopRatedMovies,
    private val getMovieGenres: GetMovieGenres
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TopRatedMovieViewModel(getTopRatedMovies, getMovieGenres) as T
    }
}
