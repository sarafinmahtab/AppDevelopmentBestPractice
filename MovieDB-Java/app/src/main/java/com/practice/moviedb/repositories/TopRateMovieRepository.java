package com.practice.moviedb.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.practice.moviedb.models.TopRatedMovie;
import com.practice.moviedb.networks.ApiService;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Informs Dagger that this class should be constructed only once.
@Singleton
public class TopRateMovieRepository {

    private ApiService apiService;

    @Inject
    public TopRateMovieRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public LiveData<TopRatedMovie> callTopRatedMoviesApi(String apiKey, String language, String page, String sortedBy) {

        final MutableLiveData<TopRatedMovie> data = new MutableLiveData<>();

        Call<TopRatedMovie> call = apiService.getTopRatedMovies(apiKey, language, page, sortedBy);

        call.enqueue(new Callback<TopRatedMovie>() {
            @Override
            public void onResponse(@NonNull Call<TopRatedMovie> call, @NonNull Response<TopRatedMovie> response) {
                TopRatedMovie movie = response.body();
                data.setValue(movie);
            }

            @Override
            public void onFailure(@NonNull Call<TopRatedMovie> call, @NonNull Throwable t) {
                Log.e("RetrofitFailure", t.toString());
            }
        });

        return data;
    }
}
