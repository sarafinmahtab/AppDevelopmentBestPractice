package com.practice.moviedatabase.dal.repositories

import com.practice.moviedatabase.dal.db.AppDao
import com.practice.moviedatabase.dal.networks.ApiService
import com.practice.moviedatabase.models.*
import com.practice.moviedatabase.models.params.GenreParams
import com.practice.moviedatabase.models.params.TopRatedMovieParams
import javax.inject.Inject

class TopRatedMovieRepository @Inject constructor(
    private val apiService: ApiService,
    private val appDao: AppDao
) {

    suspend fun fetchTopRatedMovies(
        params: TopRatedMovieParams
    ): Result<TopRatedMovie> {

        return try {

            val result = apiService.getTopRatedMoviesAsync(
                params.apiKey, params.language,
                params.page, params.sortedBy
            ).await()

            Result.success(result)

        } catch (e: Exception) {
            Result.error<TopRatedMovie>(e)
        }
    }

    suspend fun insertMovies(movies: List<Movie>) {
        appDao.insertMovies(movies)
    }

    suspend fun loadMoviesByPage(page: Int): List<Movie> {
        return appDao.getMovies()
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

    suspend fun insertGenres(genres: List<Genre>) {
        appDao.insertGenres(genres)
    }
}
