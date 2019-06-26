package com.practice.moviedatabase.dal.networks

import com.practice.moviedatabase.models.TopRatedMovie
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top_rated")
    fun getTopRatedMoviesAsync(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: String,
        @Query("sort_by") sortBy: String
    ): Deferred<TopRatedMovie>
}
