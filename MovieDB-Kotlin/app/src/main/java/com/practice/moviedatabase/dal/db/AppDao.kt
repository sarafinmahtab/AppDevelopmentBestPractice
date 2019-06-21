package com.practice.moviedatabase.dal.db

import androidx.room.*
import com.practice.moviedatabase.dal.db.DBConstants.TABLE_MOVIE
import com.practice.moviedatabase.models.Movie

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(vararg movies: List<Movie>?)

    @Query("SELECT * FROM $TABLE_MOVIE")
    fun getMovies(): List<Movie>

    @Query("SELECT COUNT(*) FROM $TABLE_MOVIE")
    fun countTotalMovies(): Int
}
