package com.practice.moviedatabase.dal.db

object DBConstants {

    const val DATABASE_NAME = "movie-db"

    // Movie Table Constants
    const val TABLE_MOVIE = "Movie"
    const val COLUMN_MOVIE_ID = "id"
    const val COLUMN_VOTE_COUNT = "vote_count"
    const val COLUMN_VIDEO = "video"
    const val COLUMN_VOTE_AVERAGE = "vote_average"
    const val COLUMN_MOVIE_TITLE = "title"
    const val COLUMN_POPULARITY = "popularity"
    const val COLUMN_POSTER_PATH = "poster_path"
    const val COLUMN_ORIGINAL_LANGUAGE = "original_language"
    const val COLUMN_ORIGINAL_TITLE = "original_title"
    const val COLUMN_GENRE_IDS = "genre_ids"
    const val COLUMN_BACKDROP_PATH = "backdrop_path"
    const val COLUMN_ADULT = "adult"
    const val COLUMN_OVERVIEW = "overview"
    const val COLUMN_RELEASE_DATE = "release_date"

    // Genre Table Constants
    const val TABLE_GENRE = "genre"

    const val COLUMN_GENRE_ID = "id"
    const val COLUMN_GENRE_NAME = "name"
}
