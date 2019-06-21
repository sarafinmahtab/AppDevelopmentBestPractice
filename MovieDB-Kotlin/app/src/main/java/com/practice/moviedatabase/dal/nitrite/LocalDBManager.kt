package com.practice.moviedatabase.dal.nitrite

import android.content.Context
import org.dizitart.no2.Nitrite

class LocalDBManager {

    companion object {

        @Volatile private var nitriteInstance: Nitrite? = null

        fun getDBInstance(context: Context): Nitrite {
            return nitriteInstance ?: synchronized(this) {
                nitriteInstance ?: buildNitriteDB(context).also { nitriteInstance = it }
            }
        }

        private fun buildNitriteDB(context: Context): Nitrite {
            return Nitrite.builder()
                .compressed()
                .filePath(context.filesDir.path + "/app.db")
                .openOrCreate("sam", "1234")
        }
    }
}
