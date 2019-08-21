package com.practice.moviedatabase.dal.repositories

import com.practice.moviedatabase.dal.db.AppDao
import com.practice.moviedatabase.dal.networks.ApiService
import com.practice.moviedatabase.models.Genres
import com.practice.moviedatabase.models.Movie
import com.practice.moviedatabase.models.Result
import com.practice.moviedatabase.models.params.GenreParams
import com.practice.moviedatabase.models.params.TopRatedMovieParams

class TopRatedMovieRepository(
    private val networkAvailable: Boolean,
    private val apiService: ApiService,
    private val appDao: AppDao
) {

    suspend fun fetchTopRatedMovies(params: TopRatedMovieParams): Result<List<Movie>> {

        return try {

            if (networkAvailable) {

                val result = apiService.getTopRatedMoviesAsync(
                    params.apiKey, params.language,
                    params.page, params.sortedBy
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
            Result.error<List<Movie>>(e)
        }
    }

    suspend fun fetchGenres(params: GenreParams): Result<Genres> {

        return try {
            val result = apiService.getGenresAsync(
                params.apiKey,
                params.language
            ).await()

            Result.success(result)

        } catch (e: Exception) {
            Result.error<Genres>(e)
        }
    }
}
