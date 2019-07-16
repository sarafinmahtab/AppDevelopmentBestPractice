package com.practice.moviedatabase.models.params

data class TopRatedMovieParams(
    val apiKey: String,
    val language: String,
    val page: String,
    val sortedBy: String
)

data class GenreParams(
    val apiKey: String,
    val language: String
)
