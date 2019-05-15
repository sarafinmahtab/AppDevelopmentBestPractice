package com.practice.moviedb.viewmodels.factories;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.practice.moviedb.repositories.TopRatedMovieRepository;
import com.practice.moviedb.viewmodels.TopRatedMovieViewModel;

public class TopRatedMovieVMFactory implements ViewModelProvider.Factory {

    private TopRatedMovieRepository repository;

    public TopRatedMovieVMFactory(TopRatedMovieRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TopRatedMovieViewModel(repository);
    }
}
