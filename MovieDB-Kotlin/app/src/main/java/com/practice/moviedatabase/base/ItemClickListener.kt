package com.practice.moviedatabase.base

import com.practice.moviedatabase.models.Movie

interface ItemClickListener {

    fun onItemClicked(movie: Movie, outputDate: String?)
}
