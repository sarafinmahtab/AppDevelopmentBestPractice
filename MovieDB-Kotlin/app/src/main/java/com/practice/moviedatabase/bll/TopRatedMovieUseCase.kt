package com.practice.moviedatabase.bll

import com.practice.moviedatabase.base.UseCase
import com.practice.moviedatabase.dal.db.AppDao
import com.practice.moviedatabase.dal.networks.ApiService
import com.practice.moviedatabase.models.Movie
import com.practice.moviedatabase.models.Result
import com.practice.moviedatabase.models.params.TopRatedMovieParams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TopRatedMovieUseCase(
    private val internetOn: Boolean,
    private val apiService: ApiService,
    private val appDao: AppDao
) :
    UseCase<TopRatedMovieParams, Result<List<Movie>>>() {

    private val tag = this.javaClass.simpleName

    override suspend fun execute(parameters: TopRatedMovieParams):
            Result<List<Movie>> = withContext(Dispatchers.IO) {

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
//            Log.w(tag, e.toString())
            Result.error<List<Movie>>(e)
        }
    }
}
