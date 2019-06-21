package com.practice.moviedatabase.dal.repositories

import android.util.Log
import com.practice.moviedatabase.base.AppDispatcher
import com.practice.moviedatabase.base.UseCase
import com.practice.moviedatabase.dal.db.AppDao
import com.practice.moviedatabase.models.TopRatedMovie
import com.practice.moviedatabase.models.params.TopRatedMovieParams
import com.practice.moviedatabase.dal.networks.ApiService
import com.practice.moviedatabase.models.Movie
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class TopRatedMovieRepository constructor(val internetOn: Boolean,
                                          private val apiService: ApiService?,
                                          val appDao: AppDao) :
    UseCase<TopRatedMovieParams, List<Movie>>() {

    override suspend fun execute(parameters: TopRatedMovieParams): List<Movie> = withContext(AppDispatcher.io) {

        if (internetOn) {
            return@withContext getMovies(parameters.apiKey!!, parameters.language!!, parameters.page!!, parameters.sortedBy!!)
        } else {
            return@withContext appDao.getMovies()
        }
    }

    private suspend fun getMovies(
        apiKey: String, language: String, page: String, sortedBy: String): List<Movie> {

        return suspendCoroutine {

            val call = apiService?.getTopRatedMovies(apiKey, language, page, sortedBy)

            call?.enqueue(object : Callback<TopRatedMovie> {

                override fun onFailure(call: Call<TopRatedMovie>, t: Throwable) {

                    Log.d("RetrofitFailure", t.message!!)
                    it.resumeWithException(t)
                }

                override fun onResponse(call: Call<TopRatedMovie>, response: Response<TopRatedMovie>) {

                    Log.d("RetrofitResponse", response.body().toString())
                    it.resume(response.body()!!.movies!!)
                    appDao.insertMovies(response.body()!!.movies!!)
                }
            })
        }
    }
}
