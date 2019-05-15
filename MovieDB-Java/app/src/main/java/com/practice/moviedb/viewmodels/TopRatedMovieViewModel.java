package com.practice.moviedb.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.practice.moviedb.models.TopRatedMovie;

public class TopRatedMovieViewModel extends ViewModel {

    private LiveData<TopRatedMovie> topRatedMovieLiveData;
}
