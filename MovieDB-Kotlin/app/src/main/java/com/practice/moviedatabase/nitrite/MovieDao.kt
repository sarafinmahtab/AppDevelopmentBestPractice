package com.practice.moviedatabase.nitrite

import com.practice.moviedatabase.models.TopRatedMovie

interface MovieDao {
    fun insert(topRatedMovie : TopRatedMovie)
    fun get() : TopRatedMovie
}