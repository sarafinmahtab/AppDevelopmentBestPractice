package com.practice.moviedatabase.repositories

import android.content.Context
import android.util.Log
import com.practice.moviedatabase.base.AppDispatcher
import com.practice.moviedatabase.base.UseCase
import com.practice.moviedatabase.models.TopRatedMovie
import com.practice.moviedatabase.models.params.TopRatedMovieParams
import com.practice.moviedatabase.networks.ApiService
import com.practice.moviedatabase.nitrite.DBConstants.topRatedMovieCollection
import com.practice.moviedatabase.nitrite.LocalDBManager
import com.practice.moviedatabase.nitrite.MovieDao
import com.practice.moviedatabase.utility.SystemActionCheck
import kotlinx.coroutines.withContext
import org.dizitart.no2.Nitrite
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class TopRatedMovieRepository constructor(context: Context, private var apiService: ApiService?, val db: MovieDao) :
    UseCase<TopRatedMovieParams, TopRatedMovie>() {
    val appContext: Context = context.applicationContext

    override suspend fun execute(parameters: TopRatedMovieParams): TopRatedMovie = withContext(AppDispatcher.io) {

        if (SystemActionCheck.isInternetOn(appContext)) {
            return@withContext getMovies(
                parameters.apiKey!!,
                parameters.language!!,
                parameters.page!!,
                parameters.sortedBy!!
            )
        } else {
            return@withContext retrieveMoviesFromLocal(appContext)
        }
    }

    private fun retrieveMoviesFromLocal(context: Context): TopRatedMovie {
        return db.get()
    }

    private fun updateToLocalDB(result: Any) {
        val topRatedMovie = result as TopRatedMovie
        db.insert(topRatedMovie)
    }

    private suspend fun getMovies(
        apiKey: String, language: String, page: String, sortedBy: String
    ): TopRatedMovie {

        return suspendCoroutine {

            val call = apiService?.getTopRatedMovies(apiKey, language, page, sortedBy)

            call?.enqueue(object : Callback<TopRatedMovie> {

                override fun onFailure(call: Call<TopRatedMovie>, t: Throwable) {

                    Log.d("RetrofitFailure", t.message)
                    it.resumeWithException(t)
                }

                override fun onResponse(call: Call<TopRatedMovie>, response: Response<TopRatedMovie>) {

                    Log.d("RetrofitResponse", response.body().toString())
                    it.resume(response.body()!!)
                    updateToLocalDB(response.body()!!)
                }
            })
        }
    }
}
