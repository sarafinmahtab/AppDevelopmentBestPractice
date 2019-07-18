package com.practice.moviedatabase.dal.repositories

import com.practice.moviedatabase.bll.TopRatedMovieUseCase
import com.practice.moviedatabase.models.Movie
import com.practice.moviedatabase.models.Result
import com.practice.moviedatabase.models.params.TopRatedMovieParams

class TopRatedMovieRepository(private val topRatedMovieUseCase: TopRatedMovieUseCase) {

    suspend fun fetchTopRatedMovies(params: TopRatedMovieParams): Result<List<Movie>> {

        return topRatedMovieUseCase(params)
    }
}
