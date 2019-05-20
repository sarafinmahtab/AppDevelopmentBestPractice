package com.practice.moviedatabase.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.practice.moviedatabase.models.TopRatedMovie
import com.practice.moviedatabase.networks.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// Informs Dagger that this class should be constructed only once.
class TopRatedMovieRepository constructor(private var apiService: ApiService?) {

    fun callTopRatedMoviesApi(apiKey: String, language: String, page: String, sortedBy: String) : LiveData<TopRatedMovie> {

        val data = MutableLiveData<TopRatedMovie>()
        val call = apiService?.getTopRatedMovies(apiKey, language, page, sortedBy)

        call?.enqueue(object : Callback<TopRatedMovie> {

            override fun onFailure(call: Call<TopRatedMovie>, t: Throwable) {

                Log.d("RetrofitResponse", t.message)
            }

            override fun onResponse(call: Call<TopRatedMovie>, response: Response<TopRatedMovie>) {

                Log.d("RetrofitResponse", response.body().toString())

                data.value = response.body()
            }
        })

        return data
    }
}
