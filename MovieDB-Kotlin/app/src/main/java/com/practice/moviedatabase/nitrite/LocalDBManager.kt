package com.practice.moviedatabase.nitrite

import android.content.Context
import com.practice.moviedatabase.models.TopRatedMovie
import org.dizitart.no2.Nitrite

class LocalDBManager : MovieDao {

    override fun get(): TopRatedMovie {
        val dbRepository =
            nitriteInstance?.getRepository(DBConstants.topRatedMovieCollection, TopRatedMovie::class.java)
        return dbRepository!!.find().firstOrDefault()
    }

    override fun insert(topRatedMovie: TopRatedMovie) {
        val dbRepository =
            nitriteInstance?.getRepository(DBConstants.topRatedMovieCollection, TopRatedMovie::class.java)
        dbRepository?.insert(topRatedMovie)
    }

    private var nitriteInstance: Nitrite? = null

    companion object {

        @Volatile
        private var db: LocalDBManager? = null

        fun getDBInstance(context: Context): LocalDBManager {
            db = LocalDBManager()
            db!!.nitriteInstance ?: synchronized(this) {
                db!!.nitriteInstance ?: buildNitriteDB(context).also { db!!.nitriteInstance = it }
            }
            return db!!
        }

        private fun buildNitriteDB(context: Context): Nitrite {
            return Nitrite.builder()
                .compressed()
                .filePath(context.filesDir.path + "/app.db")
                .openOrCreate("sam", "1234")
        }
    }
}
