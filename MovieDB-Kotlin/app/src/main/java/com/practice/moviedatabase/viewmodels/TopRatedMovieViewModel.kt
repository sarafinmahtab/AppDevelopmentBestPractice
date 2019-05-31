package com.practice.moviedatabase.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.practice.moviedatabase.base.BaseViewModel
import com.practice.moviedatabase.models.TopRatedMovie
import com.practice.moviedatabase.models.params.TopRatedMovieParams
import com.practice.moviedatabase.repositories.TopRatedMovieRepository
import com.practice.moviedatabase.utility.SystemActionCheck
import com.practice.moviedatabase.waspdb.WaspDBManager
import com.practice.moviedatabase.waspdb.WaspHashConstants.topRatedMovieHash
import kotlinx.coroutines.launch

class TopRatedMovieViewModel(private var repository: TopRatedMovieRepository) : BaseViewModel() {

    val topRateMovieLiveData: MutableLiveData<TopRatedMovie> = MutableLiveData()

    fun requestTopRatedMoviesApi(context: Context, params: TopRatedMovieParams) {
        refreshTopRatedMovie(context, params)
    }

    private fun refreshTopRatedMovie(context: Context, params: TopRatedMovieParams) {

        uiScope.launch {
            if (SystemActionCheck.isInternetOn(context)) {

                val topRatedMovie = topRateMovieLiveData.value

                if (topRatedMovie?.results != null) {

                    Log.d("ViewModel", "Returning previous TopRatedMovie object")

                    return@launch
                }

                Log.d("ViewModel", "Initializing new TopRatedMovie object")

                val result = repository(params)
                topRateMovieLiveData.value = result

                updateToWaspDB(context, result)

            } else {
                retrieveFromWaspDB(context)
            }
        }
    }

    private fun updateToWaspDB(
        context: Context,
        result: TopRatedMovie
    ) {
        val waspDb = WaspDBManager.instance?.getDatabase(context)

        val topRatedMovies = waspDb?.openOrCreateHash(topRatedMovieHash)

        topRatedMovies?.put(topRatedMovieHash, result)
    }

    private fun retrieveFromWaspDB(context: Context) {
        val waspDb = WaspDBManager.instance?.getDatabase(context)

        if (waspDb!!.existsHash(topRatedMovieHash)) {
            val topRatedMovies = waspDb.openOrCreateHash(topRatedMovieHash)

            topRateMovieLiveData.value = topRatedMovies.get<TopRatedMovie>(topRatedMovieHash)
        }
    }
}
