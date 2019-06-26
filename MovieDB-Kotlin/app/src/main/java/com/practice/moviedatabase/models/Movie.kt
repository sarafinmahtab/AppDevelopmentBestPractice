package com.practice.moviedatabase.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.practice.moviedatabase.dal.db.DBConstants.COLUMN_ADULT
import com.practice.moviedatabase.dal.db.DBConstants.COLUMN_BACKDROP_PATH
import com.practice.moviedatabase.dal.db.DBConstants.COLUMN_GENRE_IDS
import com.practice.moviedatabase.dal.db.DBConstants.COLUMN_MOVIE_ID
import com.practice.moviedatabase.dal.db.DBConstants.COLUMN_MOVIE_TITLE
import com.practice.moviedatabase.dal.db.DBConstants.COLUMN_ORIGINAL_LANGUAGE
import com.practice.moviedatabase.dal.db.DBConstants.COLUMN_ORIGINAL_TITLE
import com.practice.moviedatabase.dal.db.DBConstants.COLUMN_OVERVIEW
import com.practice.moviedatabase.dal.db.DBConstants.COLUMN_POPULARITY
import com.practice.moviedatabase.dal.db.DBConstants.COLUMN_POSTER_PATH
import com.practice.moviedatabase.dal.db.DBConstants.COLUMN_RELEASE_DATE
import com.practice.moviedatabase.dal.db.DBConstants.COLUMN_VIDEO
import com.practice.moviedatabase.dal.db.DBConstants.COLUMN_VOTE_AVERAGE
import com.practice.moviedatabase.dal.db.DBConstants.COLUMN_VOTE_COUNT
import com.practice.moviedatabase.dal.db.DBConstants.TABLE_MOVIE

@Entity(tableName = TABLE_MOVIE)
data class Movie (

    @PrimaryKey
    @SerializedName("id")
    @Expose
    @ColumnInfo(name = COLUMN_MOVIE_ID) var id: Int,

    @SerializedName("vote_count")
    @Expose
    @ColumnInfo(name = COLUMN_VOTE_COUNT) var voteCount: Int,

    @SerializedName("video")
    @Expose
    @ColumnInfo(name = COLUMN_VIDEO) var isVideo: Boolean,

    @SerializedName("vote_average")
    @Expose
    @ColumnInfo(name = COLUMN_VOTE_AVERAGE) var voteAverage: Double,

    @SerializedName("title")
    @Expose
    @ColumnInfo(name = COLUMN_MOVIE_TITLE) var title: String?,

    @SerializedName("popularity")
    @Expose
    @ColumnInfo(name = COLUMN_POPULARITY) var popularity: Double,

    @SerializedName("poster_path")
    @Expose
    @ColumnInfo(name = COLUMN_POSTER_PATH) var posterPath: String?,

    @SerializedName("original_language")
    @Expose
    @ColumnInfo(name = COLUMN_ORIGINAL_LANGUAGE) var originalLanguage: String?,

    @SerializedName("original_title")
    @Expose
    @ColumnInfo(name = COLUMN_ORIGINAL_TITLE) var originalTitle: String?,

    @SerializedName("genre_ids")
    @Expose
    @ColumnInfo(name = COLUMN_GENRE_IDS) var genreIds: ArrayList<Int>?,

    @SerializedName("backdrop_path")
    @Expose
    @ColumnInfo(name = COLUMN_BACKDROP_PATH) var backdropPath: String?,

    @SerializedName("adult")
    @Expose
    @ColumnInfo(name = COLUMN_ADULT) var isAdult: Boolean,

    @SerializedName("overview")
    @Expose
    @ColumnInfo(name = COLUMN_OVERVIEW) var overview: String?,

    @SerializedName("release_date")
    @Expose
    @ColumnInfo(name = COLUMN_RELEASE_DATE) var releaseDate: String?
)
