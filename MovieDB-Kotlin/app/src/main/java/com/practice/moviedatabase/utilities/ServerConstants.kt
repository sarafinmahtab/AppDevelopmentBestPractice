package com.practice.moviedatabase.utilities

import java.text.SimpleDateFormat
import java.util.*

object ServerConstants {

    const val BASE_URL = "https://api.themoviedb.org/3/movie/"
    const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w600_and_h900_bestv2"

    val inputDateFormat: SimpleDateFormat
        get() = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputDateFormat: SimpleDateFormat
        get() = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())
}
