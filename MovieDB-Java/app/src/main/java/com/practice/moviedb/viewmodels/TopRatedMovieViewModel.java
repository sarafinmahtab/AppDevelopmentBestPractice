package com.practice.moviedb.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.practice.moviedb.models.TopRatedMovie;
import com.practice.moviedb.repositories.TopRateMovieRepository;

import javax.inject.Inject;

public class TopRatedMovieViewModel extends ViewModel {

    private LiveData<TopRatedMovie> topRatedMovieLiveData;

    private TopRateMovieRepository repository;

    // Instructs Dagger 2 to provide the UserRepository parameter.
    @Inject
    public TopRatedMovieViewModel(TopRateMovieRepository repository) {
        this.repository = repository;
    }

    public void initTopRatedMovieFromRepo(String apiKey, String language, String page, String sortedBy) {
        if (topRatedMovieLiveData != null) {
            // ViewModel is created on a per-Fragment basis, so the userId
            // doesn't change.
            return;
        }

        topRatedMovieLiveData = repository.callTopRatedMoviesApi(apiKey, language, page, sortedBy);
    }

    public LiveData<TopRatedMovie> getTopRatedMovieLiveData() {
        return topRatedMovieLiveData;
    }
}
