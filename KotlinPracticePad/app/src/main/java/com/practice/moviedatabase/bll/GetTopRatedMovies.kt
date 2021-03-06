package com.practice.moviedatabase.bll

import com.practice.moviedatabase.base.UseCase
import com.practice.moviedatabase.dal.repositories.TopRatedMovieRepository
import com.practice.moviedatabase.helpers.ResourceHolder
import com.practice.moviedatabase.models.TopRatedMovie
import com.practice.moviedatabase.models.params.TopRatedMovieParams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetTopRatedMovies @Inject constructor(
    private val repository: TopRatedMovieRepository
) : UseCase<TopRatedMovieParams, ResourceHolder<TopRatedMovie>>() {

    override suspend fun execute(parameters: TopRatedMovieParams): ResourceHolder<TopRatedMovie> =
        withContext(Dispatchers.IO) {

            return@withContext repository.fetchTopRatedMovies(parameters)
        }
}
