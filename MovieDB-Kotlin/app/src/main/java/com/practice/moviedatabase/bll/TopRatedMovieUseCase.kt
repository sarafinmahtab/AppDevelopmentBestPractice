package com.practice.moviedatabase.bll

import com.practice.moviedatabase.base.UseCase
import com.practice.moviedatabase.dal.repositories.TopRatedMovieRepository
import com.practice.moviedatabase.models.Movie
import com.practice.moviedatabase.models.Result
import com.practice.moviedatabase.models.params.TopRatedMovieParams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TopRatedMovieUseCase(
    private val repository: TopRatedMovieRepository
) :
    UseCase<TopRatedMovieParams, Result<List<Movie>>>() {

    override suspend fun execute(parameters: TopRatedMovieParams):
            Result<List<Movie>> = withContext(Dispatchers.IO) {

        return@withContext repository.fetchTopRatedMovies(parameters)
    }
}
