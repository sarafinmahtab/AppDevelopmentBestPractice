package com.practice.moviedatabase.dal.db

import androidx.room.*
import com.practice.moviedatabase.dal.db.DBConstants.COLUMN_VOTE_AVERAGE
import com.practice.moviedatabase.dal.db.DBConstants.TABLE_GENRE
import com.practice.moviedatabase.dal.db.DBConstants.TABLE_MOVIE
import com.practice.moviedatabase.models.Genre
import com.practice.moviedatabase.models.Movie

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>)

    @Query("SELECT * FROM $TABLE_MOVIE ORDER BY $COLUMN_VOTE_AVERAGE DESC")
    fun getMovies(): List<Movie>

    @Query("SELECT COUNT(*) FROM $TABLE_MOVIE")
    fun countTotalMovies(): Int


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenres(genres: List<Genre>)

    @Query("SELECT * FROM $TABLE_GENRE")
    fun getGenres(): List<Genre>
}
