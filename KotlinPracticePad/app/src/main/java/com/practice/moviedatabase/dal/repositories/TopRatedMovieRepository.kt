package com.practice.moviedatabase.dal.repositories

import com.practice.moviedatabase.dal.db.AppDao
import com.practice.moviedatabase.dal.networks.ApiService
import com.practice.moviedatabase.dal.networks.ServerConstants
import com.practice.moviedatabase.helpers.ResourceHolder
import com.practice.moviedatabase.models.Genre
import com.practice.moviedatabase.models.Genres
import com.practice.moviedatabase.models.Movie
import com.practice.moviedatabase.models.TopRatedMovie
import com.practice.moviedatabase.models.params.TopRatedMovieParams
import javax.inject.Inject

class TopRatedMovieRepository @Inject constructor(
    private val apiService: ApiService,
    private val appDao: AppDao
) {

    suspend fun fetchTopRatedMovies(
        params: TopRatedMovieParams
    ): ResourceHolder<TopRatedMovie> {

        return try {

            val result = apiService.getTopRatedMoviesAsync(
                ServerConstants.apiKey, ServerConstants.language,
                params.page, ServerConstants.sortedBy
            )

            ResourceHolder.success(result)

        } catch (e: Exception) {
            ResourceHolder.error<TopRatedMovie>(e)
        }
    }

    suspend fun insertMovies(movies: List<Movie>) {
        appDao.insertMovies(movies)
    }

    suspend fun loadMoviesByPage(page: Int): List<Movie> {
        return appDao.getMovies()
    }

    suspend fun fetchGenres(): ResourceHolder<Genres> {

        return try {
            val result = apiService.getGenresAsync(
                ServerConstants.apiKey, ServerConstants.language
            )

            ResourceHolder.success(result)

        } catch (e: Exception) {
            ResourceHolder.error<Genres>(e)
        }
    }

    suspend fun insertGenres(genres: List<Genre>) {
        appDao.insertGenres(genres)
    }
}
