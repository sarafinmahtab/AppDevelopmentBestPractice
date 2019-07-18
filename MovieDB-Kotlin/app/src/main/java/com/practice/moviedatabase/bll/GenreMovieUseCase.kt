package com.practice.moviedatabase.bll

import com.practice.moviedatabase.base.UseCase
import com.practice.moviedatabase.dal.db.AppDao
import com.practice.moviedatabase.dal.networks.ApiService
import com.practice.moviedatabase.models.Genres
import com.practice.moviedatabase.models.Result
import com.practice.moviedatabase.models.params.GenreParams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GenreMovieUseCase(
    private val internetOn: Boolean,
    private val apiService: ApiService,
    private val appDao: AppDao
) : UseCase<GenreParams, Result<Genres>>() {

    override suspend fun execute(parameters: GenreParams): Result<Genres> = withContext(Dispatchers.IO) {

        return@withContext try {
            val result = apiService.getGenresAsync(
                parameters.apiKey,
                parameters.language
            ).await()

            Result.success(result)

        } catch (e: Exception) {
            Result.error<Genres>(e)
        }
    }
}
