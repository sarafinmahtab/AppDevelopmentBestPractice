package com.practice.moviedatabase.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practice.moviedatabase.repositories.TopRatedMovieRepository
import com.practice.moviedatabase.viewmodels.TopRatedMovieViewModel

class TopRatedViewModelFactory(private var repository: TopRatedMovieRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TopRatedMovieViewModel(repository) as T
    }
}
