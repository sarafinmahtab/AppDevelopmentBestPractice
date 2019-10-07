package com.practice.moviedatabase.dal.networks

import com.google.gson.JsonObject
import com.practice.moviedatabase.models.Genres
import com.practice.moviedatabase.models.TopRatedMovie
import com.practice.moviedatabase.models.auth.RequestToken
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("movie/top_rated")
    fun getTopRatedMoviesAsync(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: String,
        @Query("sort_by") sortBy: String
    ): Deferred<TopRatedMovie>

    @GET("genre/movie/list")
    fun getGenresAsync(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Deferred<Genres>

    @GET("authentication/token/new")
    fun getTokenAsync(
        @Query("api_key") apiKey: String
    ): Deferred<RequestToken>

    @POST("authentication/token/validate_with_login")
    fun createSessionAsync(
        @Query("api_key") apiKey: String,
        @Body body: JsonObject
    ): Deferred<RequestToken>
}
