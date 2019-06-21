package com.practice.moviedatabase.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TopRatedMovie (
    @SerializedName("page")
    @Expose
    var page: Int = 0,
    @SerializedName("total_results")
    @Expose
    var totalResults: Int,
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int,
    @SerializedName("movies")
    @Expose
    var movies: List<Movie>?
)
