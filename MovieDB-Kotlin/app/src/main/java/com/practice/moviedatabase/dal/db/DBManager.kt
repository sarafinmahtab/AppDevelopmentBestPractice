package com.practice.moviedatabase.dal.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.practice.moviedatabase.dal.db.DBConstants.DATABASE_NAME
import com.practice.moviedatabase.models.Movie

@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = true)
@TypeConverters(Converters::class)
abstract class DBManager: RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object {

        fun getInstance(application: Application): AppDao {

            return Room.databaseBuilder(
                application,
                DBManager::class.java,
                DATABASE_NAME
            ).allowMainThreadQueries().build().appDao()
        }
    }
}
