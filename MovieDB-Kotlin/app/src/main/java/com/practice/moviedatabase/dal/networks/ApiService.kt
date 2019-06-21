package com.practice.moviedatabase.dal.networks

import com.practice.moviedatabase.models.TopRatedMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: String,
        @Query("sort_by") sortBy: String
    ): Call<TopRatedMovie>
}
