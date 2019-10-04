package com.practice.moviedatabase.base

import com.practice.moviedatabase.di.DaggerAppComponent

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MovieDB : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}
