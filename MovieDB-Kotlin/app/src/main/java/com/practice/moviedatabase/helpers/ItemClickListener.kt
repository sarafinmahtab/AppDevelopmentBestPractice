package com.practice.moviedatabase.helpers

import com.practice.moviedatabase.models.Movie

interface ItemClickListener {

    fun onItemClicked(movie: Movie, outputDate: String?)
}
