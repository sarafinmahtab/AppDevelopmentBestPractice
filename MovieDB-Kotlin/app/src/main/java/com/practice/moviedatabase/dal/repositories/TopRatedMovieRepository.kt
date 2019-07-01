package com.practice.moviedatabase.dal.repositories

import android.util.Log
import com.practice.moviedatabase.base.AppDispatcher
import com.practice.moviedatabase.base.UseCase
import com.practice.moviedatabase.dal.db.AppDao
import com.practice.moviedatabase.models.params.TopRatedMovieParams
import com.practice.moviedatabase.dal.networks.ApiService
import com.practice.moviedatabase.models.Movie
import com.practice.moviedatabase.models.Result
import kotlinx.coroutines.withContext

class TopRatedMovieRepository(
    private val internetOn: Boolean,
    private val apiService: ApiService,
    private val appDao: AppDao) :
    UseCase<TopRatedMovieParams, Result<List<Movie>>>() {

    private val tag = this.javaClass.simpleName

    override suspend fun execute(parameters: TopRatedMovieParams): Result<List<Movie>> = withContext(AppDispatcher.io) {

        return@withContext try {

            if (internetOn) {

                val result = apiService.getTopRatedMoviesAsync(
                    parameters.apiKey, parameters.language,
                    parameters.page, parameters.sortedBy
                ).await()

                if (result.movies.isNullOrEmpty()) {
                    Result.error<List<Movie>>(Exception("Movies value is Null or Empty"))
                } else {
                    appDao.insertMovies(result.movies!!)
                    Result.success(result.movies!!)
                }
            } else {

                val movies = appDao.getMovies()

                if (movies.isNullOrEmpty()) {
                    Result.error<List<Movie>>(Exception("Movies value is Null or Empty"))
                } else {
                    Result.success(movies)
                }
            }

        } catch (e: Exception) {
            Log.w(tag, e.toString())
            Result.error<List<Movie>>(e)
        }
    }
}
