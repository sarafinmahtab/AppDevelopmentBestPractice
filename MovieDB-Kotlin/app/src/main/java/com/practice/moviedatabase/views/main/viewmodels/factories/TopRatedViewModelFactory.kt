package com.practice.moviedatabase.views.main.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practice.moviedatabase.bll.GenreMovieUseCase
import com.practice.moviedatabase.bll.TopRatedMovieUseCase
import com.practice.moviedatabase.dal.repositories.TopRatedMovieRepository
import com.practice.moviedatabase.views.main.viewmodels.TopRatedMovieViewModel

class TopRatedViewModelFactory(
    private val topRatedMovieUseCase: TopRatedMovieUseCase,
    private val genreMovieUseCase: GenreMovieUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TopRatedMovieViewModel(topRatedMovieUseCase, genreMovieUseCase) as T
    }
}
