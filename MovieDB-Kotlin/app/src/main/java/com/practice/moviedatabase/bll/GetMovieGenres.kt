package com.practice.moviedatabase.bll

import com.practice.moviedatabase.base.UseCase
import com.practice.moviedatabase.dal.repositories.TopRatedMovieRepository
import com.practice.moviedatabase.models.Genres
import com.practice.moviedatabase.models.Result
import com.practice.moviedatabase.models.params.GenreParams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetMovieGenres(
    private val repository: TopRatedMovieRepository
) : UseCase<GenreParams, Result<Genres>>() {

    override suspend fun execute(parameters: GenreParams): Result<Genres> = withContext(Dispatchers.IO) {

        return@withContext repository.fetchGenres(parameters)
    }
}
