package com.practice.moviedatabase.bll

import com.practice.moviedatabase.base.UseCase
import com.practice.moviedatabase.dal.repositories.TopRatedMovieRepository
import com.practice.moviedatabase.models.Genres
import com.practice.moviedatabase.helpers.ResourceHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMovieGenres @Inject constructor(
    private val repository: TopRatedMovieRepository
) : UseCase<Any, ResourceHolder<Genres>>() {

    override suspend fun execute(parameters: Any): ResourceHolder<Genres> =
        withContext(Dispatchers.IO) {

            return@withContext repository.fetchGenres()
        }
}
