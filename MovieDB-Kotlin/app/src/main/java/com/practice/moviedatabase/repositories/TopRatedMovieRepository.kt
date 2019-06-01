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
import com.practice.moviedatabase.utility.SystemActionCheck
import com.practice.moviedatabase.waspdb.WaspDBManager
import com.practice.moviedatabase.waspdb.WaspHashConstants
import kotlinx.coroutines.withContext
import org.dizitart.no2.objects.ObjectRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class TopRatedMovieRepository constructor(private var context: Context, private var apiService: ApiService?) :
    UseCase<TopRatedMovieParams, TopRatedMovie>() {

    override suspend fun execute(parameters: TopRatedMovieParams): TopRatedMovie = withContext(AppDispatcher.io) {

        if (SystemActionCheck.isInternetOn(context)) {
            return@withContext getMovies(parameters.apiKey!!, parameters.language!!, parameters.page!!, parameters.sortedBy!!)
        } else {
            return@withContext retrieveMoviesFromLocal(context)
        }
    }

    private fun retrieveMoviesFromLocal(context: Context): TopRatedMovie {

        val db = LocalDBManager.getDBInstance(context)

        val dbRepository = db.getRepository(topRatedMovieCollection, TopRatedMovie::class.java)

        return dbRepository.find().firstOrDefault()
    }

    private fun updateToLocalDB(context: Context, result: Any) {

        val topRatedMovie = result as TopRatedMovie

        val db = LocalDBManager.getDBInstance(context)

        val dbRepository = db.getRepository(topRatedMovieCollection, TopRatedMovie::class.java)

        dbRepository.insert(topRatedMovie)
    }

    private suspend fun getMovies(
        apiKey: String, language: String, page: String, sortedBy: String): TopRatedMovie {

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
                    updateToLocalDB(context, response.body()!!)
                }
            })
        }
    }
}
