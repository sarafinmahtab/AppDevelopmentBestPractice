package com.practice.moviedatabase.dal.networks

import java.text.SimpleDateFormat
import java.util.*

object ServerConstants {

    // Urls
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w600_and_h900_bestv2"

    // API Params
    const val apiKey = "6371db70ffc8e719f981e307e397452e"
    const val language = "en-US"
    const val sortedBy = "vote_average.asc"

    val inputDateFormat: SimpleDateFormat
        get() = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputDateFormat: SimpleDateFormat
        get() = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())
}
