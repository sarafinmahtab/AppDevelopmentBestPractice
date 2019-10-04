package com.practice.moviedatabase.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practice.moviedatabase.views.main.viewmodels.TopRatedMovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Module used to define the connection between the framework's [ViewModelProvider.Factory] and
 * our own implementation: [IOSchedViewModelFactory].
 */
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(TopRatedMovieViewModel::class)
    abstract fun bindTopRatedMovieViewModel(viewModel: TopRatedMovieViewModel): ViewModel
}
