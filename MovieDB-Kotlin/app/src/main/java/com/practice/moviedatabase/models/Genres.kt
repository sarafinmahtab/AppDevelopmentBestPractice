package com.practice.moviedatabase.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.practice.moviedatabase.dal.db.DBConstants.COLUMN_GENRE_ID
import com.practice.moviedatabase.dal.db.DBConstants.COLUMN_GENRE_NAME
import com.practice.moviedatabase.dal.db.DBConstants.TABLE_GENRE

data class Genres (
    @SerializedName("genres")
    @Expose
    var genres: List<Genre>?
)

@Entity(tableName = TABLE_GENRE)
data class Genre (
    @PrimaryKey
    @SerializedName("id")
    @Expose
    @ColumnInfo(name = COLUMN_GENRE_ID) var id: Int = 0,
    @SerializedName("name")
    @Expose
    @ColumnInfo(name = COLUMN_GENRE_NAME) var name: String
)
