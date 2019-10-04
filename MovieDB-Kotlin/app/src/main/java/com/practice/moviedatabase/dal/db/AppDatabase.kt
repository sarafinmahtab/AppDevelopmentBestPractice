package com.practice.moviedatabase.dal.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.practice.moviedatabase.models.Genre
import com.practice.moviedatabase.models.Movie

@Database(
    entities = [Movie::class, Genre::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getAppDao(): AppDao
}
