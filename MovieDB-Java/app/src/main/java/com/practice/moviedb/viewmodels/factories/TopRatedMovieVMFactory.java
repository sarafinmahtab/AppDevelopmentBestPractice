package com.practice.moviedb.viewmodels.factories;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.practice.moviedb.repositories.TopRateMovieRepository;
import com.practice.moviedb.viewmodels.TopRatedMovieViewModel;

public class TopRatedMovieVMFactory implements ViewModelProvider.Factory {

    private TopRateMovieRepository repository;

    public TopRatedMovieVMFactory(TopRateMovieRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TopRatedMovieViewModel(repository);
    }
}
