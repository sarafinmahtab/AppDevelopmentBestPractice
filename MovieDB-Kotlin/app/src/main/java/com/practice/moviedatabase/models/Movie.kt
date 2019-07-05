package com.practice.moviedatabase.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.practice.moviedatabase.dal.db.Converters
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
data class Movie(

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
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        Converters.toIntArray(parcel.readString() ?: ""),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(voteCount)
        parcel.writeByte(if (isVideo) 1 else 0)
        parcel.writeDouble(voteAverage)
        parcel.writeString(title)
        parcel.writeDouble(popularity)
        parcel.writeString(posterPath)
        parcel.writeString(originalLanguage)
        parcel.writeString(originalTitle)
        parcel.writeString(Converters.fromIntArray(genreIds ?: arrayListOf()))
        parcel.writeString(backdropPath)
        parcel.writeByte(if (isAdult) 1 else 0)
        parcel.writeString(overview)
        parcel.writeString(releaseDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}
