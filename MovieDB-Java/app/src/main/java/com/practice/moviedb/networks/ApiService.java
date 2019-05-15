package com.practice.moviedb.networks;

import com.practice.moviedb.models.TopRatedMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("current.json")
    Call<TopRatedMovie> getTopRatedMovies(@Query("api_key") String apiKey,
                                          @Query("language") String language,
                                          @Query("page") String page,
                                          @Query("sort_by") String sortBy);
}
